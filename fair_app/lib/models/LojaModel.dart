import 'dart:async';
import 'dart:collection';
import 'dart:convert';

import 'package:http/http.dart' as http;

class LojaModel {
  final int idLoja;
  final String nome;
  final String cnpj;
  final String telefone;

  const LojaModel({
    required this.idLoja,
    required this.nome,
    required this.cnpj,
    required this.telefone,
  });

  static Future<String> addLoja(LojaModel loja) async {
    final response = await http.post(Uri.parse('http://25.76.67.204:8080/loja'),
        body: loja.toJson().toString(),
        headers: {"Content-Type": "application/json"});
    if (response.statusCode == 201) {
      return response.body;
    } else {
      return "Error";
    }
  }

  static Future<String> atualizaLoja(LojaModel loja) async {
    final response = await http.post(Uri.parse('http://25.76.67.204:8080/loja/update'),
        body: loja.toJson().toString(),
        headers: {"Content-Type": "application/json"});
    if (response.statusCode == 201) {
      return response.body;
    } else {
      return "Error";
    }
  }

  static Future<HashMap> findById(idLoja) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/loja/findById/' + idLoja));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String, dynamic>();
      HashMap dadosLoja = HashMap();
      dadosLoja["idLoja"] = parsed["idLoja"];
      dadosLoja["nome"] = parsed["nome"];
      dadosLoja["cnpj"] = parsed["cnpj"];
      dadosLoja["telefone"] = parsed["telefone"];
      return dadosLoja;
    }
    return HashMap();
  }

  Map<String, dynamic> toJson() => {
        '"idLoja"': "\"" + idLoja.toString() + "\"",
        '"nome"': "\"" + nome + "\"",
        '"cnpj"': "\"" + cnpj + "\"",
        '"telefone"': "\"" + telefone + "\"",
      };

  factory LojaModel.fromJson(Map<String, dynamic> json) {
    return LojaModel(
      idLoja: json['idPedido'],
      nome: json['nome'],
      telefone: json['telefone'],
      cnpj: json['cpf'],
    );
  }
}
