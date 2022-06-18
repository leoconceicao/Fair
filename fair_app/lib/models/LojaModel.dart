import 'dart:async';

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

  Map<String, dynamic> toJson() => {
        '"idLoja"': "\"" + idLoja.toString() + "\"",
        '"nome"': "\"" + nome + "\"",
        '"cnpj"': "\"" + cnpj + "\"",
        '"telefone"': "\"" + telefone + "\"",
      };
}
