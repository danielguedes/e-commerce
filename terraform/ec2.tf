# resource "aws_api_gateway_rest_api" "api" {
#   name        = "api-${var.modelo}"
#   description = "API do modelo ${var.modelo}"
#   body = "${data.template_file.api_gateway_renderer.rendered}"
# }

# resource "aws_api_gateway_deployment" "deploy" {
#   rest_api_id = "${aws_api_gateway_rest_api.api.id}"
#   stage_name  = "api"
# }

# data "template_file" "api_gateway_renderer" {
#   template = "${file("../api/api-web-simples-test-swagger-apigateway_template.json")}"

#   vars = {
#     lambda_get_list_invoke_arn = "${aws_lambda_function.lambda-get-list.invoke_arn}"
#     lambda_post_todo_invoke_arn = "${aws_lambda_function.lambda-post-todo.invoke_arn}"
#   }
# }

# resource "aws_lambda_permission" "lambda-get-list-permission" {
#   statement_id  = "AllowAPIGatewayInvoke"
#   action        = "lambda:InvokeFunction"
#   function_name = "${aws_lambda_function.lambda-get-list.arn}"
#   principal     = "apigateway.amazonaws.com"

#   source_arn    = "${aws_api_gateway_rest_api.api.execution_arn}/*/*/*"
# }

# resource "aws_lambda_permission" "lambda-post-todo-permission" {
#   statement_id  = "AllowAPIGatewayInvoke"
#   action        = "lambda:InvokeFunction"
#   function_name = "${aws_lambda_function.lambda-post-todo.arn}"
#   principal     = "apigateway.amazonaws.com"

#   source_arn    = "${aws_api_gateway_rest_api.api.execution_arn}/*/*/*"
# }