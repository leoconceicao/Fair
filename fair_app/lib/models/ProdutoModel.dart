import 'dart:async';
import 'dart:collection';
import 'dart:convert';

import 'package:http/http.dart' as http;

class ProdutoModel {
  final int idProduto;
  final String nome;
  final String tipo;
  final double preco;
  final String foto;
  final String validade;
  final double peso;

  const ProdutoModel({
    required this.idProduto,
    required this.nome,
    required this.tipo,
    required this.preco,
    required this.foto,
    required this.validade,
    required this.peso,
  });

  static Future findAll() async {
    final response =
        await http.get(Uri.parse('http://25.76.67.204:8080/produto'));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String, dynamic>();
      List<String> produtos = [];
      HashMap p = HashMap();
      for (var produto in parsed["content"]) {
        if (p[produto["nome"]] == null) {
          p[produto["nome"]] = produto["nome"];
          produtos.add(produto["nome"]);
        }
      }
      return produtos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  static Future<String> findById(String id) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/produto/findById/' + id));
    if (response.statusCode == 200) {
      return jsonDecode(response.body)["nome"];
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  static Future<String> findProductById(String id) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/produto/findById/' + id));
    if (response.statusCode == 200) {
      return response.body;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  static Future findAllByName(name) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/produto/findByName/' + name));
    if (response.statusCode == 200) {
      List<String> produtos = [];
      HashMap p = HashMap();
      for (var produto in jsonDecode(response.body)) {
        if (p[produto["nome"]] == null) {
          p[produto["nome"]] = produto["nome"];
          produtos.add(produto["nome"]);
        }
      }
      return produtos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  static Future<String> addProduto(ProdutoModel produto) async {
    final response = await http.post(
        Uri.parse('http://25.76.67.204:8080/produto'),
        body: produto.toJson().toString(),
        headers: {"Content-Type": "application/json"});
    if (response.statusCode == 201) {
      return response.body;
    } else {
      return "Error";
    }
  }

  static Future<String> deactivate(int id) async {
    final response = await http.put(Uri.parse(
        'http://25.76.67.204:8080/produto/deactivate/' + id.toString()));
    if (response.statusCode == 201) {
      return "Deleted";
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  Map<String, dynamic> toJson() => {
        '"idProduto"': idProduto.toString(),
        '"nome"': "\"" + nome + "\"",
        '"tipo"': "\"" + tipo + "\"",
        '"preco"': preco,
        '"foto"': "\"" + foto + "\"",
        '"validade"': "\"" + validade + "\"",
        '"peso"': peso.toString()
      };
}
