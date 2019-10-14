provider "aws" {
  profile    = "arpia"
  region     = "sa-east-1"
  version    = "~> 2.19"
}

variable "area" {
  default = ""
}

variable "project" {
  default = "ecommerce"
}
variable "ambiente" {
  default = "demo"
}

variable "modelo" {
  default = "ecommerce"
}

variable "certificado" {
  default = "arn:aws:acm:us-east-1:088233713798:certificate/e883e506-a2ee-4947-87d8-37fbe9dc4fe4"
}

variable "dns_zone_id" {
  default = "ZOG61WGGQNAM3"
}
variable "ec2_public_dns" {
  default = "ec2-54-158-123-86.compute-1.amazonaws.com"
}
