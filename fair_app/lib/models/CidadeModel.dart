import 'dart:async';

import 'dart:convert';

import 'package:http/http.dart' as http;

class CidadeModel {
  final int idPedido;
  final String periodicidade;
  final double peso;
  final int fkCliente;
  final int fkVendedor;

  const CidadeModel({
    required this.idPedido,
    required this.periodicidade,
    required this.peso,
    required this.fkCliente,
    required this.fkVendedor,
  });

  static Future<List<String>> get(id) async {
    List<String> pedidos = [];
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/pedido'));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String,dynamic>();
      for (var pedido in parsed["content"]) {
        pedidos.add(pedido["idPedido"].toString());
      }
      return pedidos;
    }
    return pedidos;
  }

  static Future<List<String>> getData(string) async {
    List<String> pedidos = [];
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/pedido'));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String,dynamic>();
      for (var pedido in parsed["content"]) {
        pedidos.add(pedido["idPedido"].toString());
      }
      return pedidos;
    }
    return pedidos;
  }

  factory CidadeModel.fromJson(Map<String, dynamic> json) {
    return CidadeModel(
      idPedido: json['idPedido'],
      periodicidade: json['periodicidade'],
      peso: json['peso'].toDouble(),
      fkCliente: json['fkCliente']["idPessoa"],
      fkVendedor: json['fkVendedor']["idPessoa"],
    );
  }
}

