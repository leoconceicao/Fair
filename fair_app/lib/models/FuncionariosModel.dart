import 'dart:async';

import 'package:http/http.dart' as http;

class FuncionarioModel {
  final int idFuncionario;
  String cargo;
  int fkLoja;
  int fkPessoa;

  FuncionarioModel(this.idFuncionario, this.cargo, this.fkLoja, this.fkPessoa);

  static Future<String> addFuncionario(FuncionarioModel funcionario) async {
    final response = await http.post(
        Uri.parse('http://25.76.67.204:8080/funcionario'),
        body: funcionario.toJson().toString(),
        headers: {"Content-Type": "application/json"});
    if (response.statusCode == 201) {
      return response.body;
    } else {
      return "Error";
    }
  }

  Map<String, dynamic> toJson() => {
        '"idFuncionario"': "\"" + idFuncionario.toString() + "\"",
        '"cargo"': "\"" + cargo + "\"",
        '"fkLoja"': "\"" + fkLoja.toString() + "\"",
        '"fkPessoa"': "\"" + fkPessoa.toString() + "\"",
      };
}
