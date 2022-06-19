import 'dart:async';
import 'dart:collection';
import 'dart:convert';

import 'package:http/http.dart' as http;

class PessoaModel {
  final int idPessoa;
  final String nome;
  final String telefone;
  final String cpf;
  final String email;
  final String password;

  const PessoaModel({
    required this.idPessoa,
    required this.nome,
    required this.telefone,
    required this.cpf,
    required this.email,
    required this.password,
  });

  static Future<HashMap> findByEmail(email, password) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/pessoa/findByEmail/' + email));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String, dynamic>();
      HashMap dadosPessoa = HashMap();
      dadosPessoa["idPessoa"] = parsed["idPessoa"];
      dadosPessoa["emailDb"] = parsed["email"];
      dadosPessoa["passwordDb"] = parsed["password"];
      dadosPessoa["email"] = email;
      dadosPessoa["password"] = password;
      return dadosPessoa;
    }
    return HashMap();
  }

  static Future<String> addPessoa(PessoaModel pessoa) async {
    final response = await http.post(
        Uri.parse('http://25.76.67.204:8080/pessoa'),
        body: pessoa.toJson().toString(),
        headers: {"Content-Type": "application/json"});
    if (response.statusCode == 201) {
      return response.body;
    } else {
      return "Error";
    }
  }

  static Future<String> atualizaPessoa(PessoaModel pessoa) async {
    final response = await http.post(
        Uri.parse('http://25.76.67.204:8080/pessoa/update/'),
        body: pessoa.toJson().toString(),
        headers: {"Content-Type": "application/json"});
    if (response.statusCode == 201) {
      return response.body;
    } else {
      return "Error";
    }
  }

  Map<String, dynamic> toJson() => {
        '"idPessoa"': "\"" + idPessoa.toString() + "\"",
        '"nome"': "\"" + nome + "\"",
        '"telefone"': "\"" + telefone + "\"",
        '"cpf"': "\"" + cpf + "\"",
        '"email"': "\"" + email + "\"",
        '"password"': "\"" + password + "\""
      };

  factory PessoaModel.fromJson(Map<String, dynamic> json) {
    return PessoaModel(
      idPessoa: json['idPedido'],
      nome: json['nome'],
      telefone: json['telefone'],
      cpf: json['cpf'],
      email: json['email'],
      password: json['password'],
    );
  }
}
