resource "aws_api_gateway_rest_api" "api" {
  name        = "api-${var.modelo}"
  description = "API do modelo ${var.modelo}"
  body = "${data.template_file.api_gateway_renderer.rendered}"
}

resource "aws_api_gateway_deployment" "deploy" {
  rest_api_id = "${aws_api_gateway_rest_api.api.id}"
  stage_name  = "dev"
}

data "template_file" "api_gateway_renderer" {
  template = "${file("../api/ecommerce-api.json.tpl")}"

  vars = {
    endpoint_protocol="http",
    endpoint_hostname="${var.ec2_public_dns}",
    endpoint_port="8080"
  }
}