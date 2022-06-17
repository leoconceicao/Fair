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

  static Future<HashMap> findByEmail(email) async {

    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/pessoa/findByEmail/' + email));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String,dynamic>();
      HashMap dadosPessoa = HashMap();
      dadosPessoa["email"] = parsed["email"];
      dadosPessoa["password"] = parsed["password"];
      return dadosPessoa;
    }
    return HashMap();
  }
}
