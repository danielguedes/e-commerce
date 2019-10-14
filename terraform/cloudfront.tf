## CloudFront - CDN e Caching

resource "aws_cloudfront_distribution" "cloudfront-modelo" {
  enabled             = true
  is_ipv6_enabled     = true
  default_root_object = "index.html"
  comment             = "CloudFront do modelo de referência de ${var.modelo}."
  wait_for_deployment = false

  aliases = ["${var.modelo}.arpiati.com.br"]

  origin {
    domain_name = "${aws_api_gateway_rest_api.api.id}.execute-api.us-east-1.amazonaws.com"
    origin_id   = "origin-api-${aws_api_gateway_rest_api.api.id}"

    custom_origin_config {
      origin_protocol_policy   = "http-only"
      http_port                = "80"
      https_port               = "443"
      origin_ssl_protocols     = ["TLSv1", "TLSv1.1", "TLSv1.2"]
      origin_read_timeout      = "30"
      origin_keepalive_timeout = "5"
    }
  }

  default_cache_behavior {
    # path_pattern           = "/api/*"
    target_origin_id       = "origin-api-${aws_api_gateway_rest_api.api.id}"
    allowed_methods        = ["GET", "HEAD", "OPTIONS", "PUT", "POST", "PATCH", "DELETE"]
    cached_methods         = ["GET", "HEAD"]
    viewer_protocol_policy = "allow-all"
    default_ttl            = "0"
    max_ttl                = "0"

    forwarded_values {
      query_string = false

      cookies {
        forward = "none"
      }
    }
  }

  restrictions {
    geo_restriction {
      restriction_type = "none"
    }
  }

  price_class = "PriceClass_All"

  custom_error_response {
    error_caching_min_ttl = "300"
    error_code            = "404"
    response_page_path    = "/index.html"
    response_code         = "200"
  }

  viewer_certificate {
    cloudfront_default_certificate = false
    acm_certificate_arn            = "${var.certificado}"
    minimum_protocol_version       = "TLSv1.1_2016"
    ssl_support_method             = "sni-only"
  }

  tags = {
    Project = "${var.project}"
  }
}
