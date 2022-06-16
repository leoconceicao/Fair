import 'dart:async';

import 'dart:convert';

import 'package:http/http.dart' as http;

import 'PedidoModel.dart';
import 'ProdutoModel.dart';

class ProdutoPedidoModel {
  final int idProdutoPedido;
  final ProdutoModel fkProduto;
  final PedidoModel fkPedido;
  final int quantidade;
  final double peso;
  final double preco;

  const ProdutoPedidoModel({
    required this.idProdutoPedido,
    required this.fkProduto,
    required this.fkPedido,
    required this.quantidade,
    required this.peso,
    required this.preco,
  });

  static Future get() async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/produtoPedido'));
    if (response.statusCode == 200) {
        final parsed = jsonDecode(response.body).cast<String,dynamic>();
        List<String> pedidos = [];
        for (var pedido in parsed["content"]) {
          var produto = pedido["fkProduto"];
          pedidos.add(produto["nome"]);
        }
        return pedidos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  static Future getByName(String name) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/produtoPedido/byName/name=' + name));
    if (response.statusCode == 200) {
        List<String> pedidos = [];
        for (var pedido in jsonDecode(response.body)) {
          var produto = pedido["fkProduto"];
          pedidos.add(produto["nome"]);
        }
        return pedidos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }
}
