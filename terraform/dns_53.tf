resource "aws_route53_record" "dns-frontend" {
  zone_id = "${var.dns_zone_id}"
  name    = "${var.modelo}"
  type    = "CNAME"
  ttl     = "5"
  
  records = ["${aws_cloudfront_distribution.cloudfront-modelo.domain_name}"]
}