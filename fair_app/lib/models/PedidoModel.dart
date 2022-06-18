import 'dart:async';

import 'dart:convert';

import 'package:http/http.dart' as http;

class PedidoModel {
  final int idPedido;
  final String data;
  final String periodicidade;
  final double peso;
  final int fkCliente;
  final int fkVendedor;

  const PedidoModel({
    required this.idPedido,
    required this.data,
    required this.periodicidade,
    required this.peso,
    required this.fkCliente,
    required this.fkVendedor,
  });

  static Future findPedidos(int userId) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/pedido/findPedidos/' + userId.toString()));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body);
      List<String> pedidos = [];
      for (var pedido in parsed) {
        pedidos.add("#" + pedido["idPedido"].toString() + " - " + pedido["data"]);
      }
      return pedidos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  static Future findVendas(int lojaId) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/pedido/findVendas/' + lojaId.toString()));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body);
      List<String> pedidos = [];
      for (var pedido in parsed) {
        pedidos.add("#" + pedido["idPedido"].toString() + " - " + pedido["data"]);
      }
      return pedidos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  factory PedidoModel.fromJson(Map<String, dynamic> json) {
    return PedidoModel(
      idPedido: json['idPedido'],
      data: json['data'],
      periodicidade: json['periodicidade'],
      peso: json['peso'].toDouble(),
      fkCliente: json['fkCliente']["idPessoa"],
      fkVendedor: json['fkVendedor']["idPessoa"],
    );
  }
}
