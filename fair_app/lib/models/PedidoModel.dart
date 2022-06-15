import 'dart:async';

import 'dart:convert';

import 'package:http/http.dart' as http;

class PedidoModel {
  final int idPedido;
  final String periodicidade;
  final double peso;
  final int fkCliente;
  final int fkVendedor;

  const PedidoModel({
    required this.idPedido,
    required this.periodicidade,
    required this.peso,
    required this.fkCliente,
    required this.fkVendedor,
  });

  static Future get(id) async {
    final response = await http
        .get(Uri.parse('http://10.0.2.2:8080/pedido'));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String,dynamic>();
      List<String> pedidos = [];
      for (var pedido in parsed["content"]) {
        pedidos.add(pedido["idPedido"].toString());
      }
      return pedidos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  factory PedidoModel.fromJson(Map<String, dynamic> json) {
    return PedidoModel(
      idPedido: json['idPedido'],
      periodicidade: json['periodicidade'],
      peso: json['peso'].toDouble(),
      fkCliente: json['fkCliente']["idPessoa"],
      fkVendedor: json['fkVendedor']["idPessoa"],
    );
  }
}

