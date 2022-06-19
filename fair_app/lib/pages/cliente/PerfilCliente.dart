import 'dart:async';
import 'dart:convert';

import 'package:fair_app/commons/ScreenArguments.dart';
import 'package:fair_app/models/PessoaModel.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mask_text_input_formatter/mask_text_input_formatter.dart';

class Perfil extends StatefulWidget {
  const Perfil({Key? key}) : super(key: key);

  @override
  _PerfilState createState() => _PerfilState();
}

class _PerfilState extends State<Perfil> {
  void initState() {}

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _telefoneController = TextEditingController();
  final TextEditingController _cpfController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _enderecoController = TextEditingController();
  final TextEditingController _senha01Controller = TextEditingController();
  final TextEditingController _senha02Controller = TextEditingController();
  late int _idUsuario;

  final mascaraTelefone = MaskTextInputFormatter(
      mask: '(##) #####-####',
      filter: {"#": RegExp(r'[0-9]')},
      type: MaskAutoCompletionType.lazy);

  final mascaraCPF = MaskTextInputFormatter(
      mask: '###.###.###-##',
      filter: {"#": RegExp(r'[0-9]')},
      type: MaskAutoCompletionType.lazy);

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    if (_nomeController.text == "") {
      get(args.a["userId"].toString(), context);
    }
    return Container(
        height: 500,
        child: Scaffold(
            body: SafeArea(
          child: Column(
            children: [
              const SizedBox(
                height: 30,
              ),
              TextField(
                style: const TextStyle(
                  fontSize: 14.0,
                  color: Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300,
                ),
                readOnly: true,
                controller: _nomeController,
                decoration: const InputDecoration(
                  border: UnderlineInputBorder(),
                  labelText: 'Nome',
                ),
              ),
              TextField(
                style: const TextStyle(
                  fontSize: 14.0,
                  color: Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300,
                ),
                readOnly: true,
                controller: _emailController,
                decoration: const InputDecoration(
                  border: UnderlineInputBorder(),
                  labelText: 'Email',
                ),
              ),
              TextField(
                style: const TextStyle(
                  fontSize: 14.0,
                  color: Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300,
                ),
                readOnly: true,
                controller: _telefoneController,
                decoration: const InputDecoration(
                  border: UnderlineInputBorder(),
                  labelText: 'Telefone',
                ),
              ),
              TextField(
                style: const TextStyle(
                  fontSize: 14.0,
                  color: Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300,
                ),
                readOnly: true,
                controller: _cpfController,
                decoration: const InputDecoration(
                  border: UnderlineInputBorder(),
                  labelText: 'CPF',
                ),
              ),
              TextField(
                style: const TextStyle(
                  fontSize: 14.0,
                  color: Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300,
                ),
                readOnly: true,
                controller: _enderecoController,
                decoration: const InputDecoration(
                  border: UnderlineInputBorder(),
                  labelText: 'Endereco',
                ),
              ),
              const SizedBox(
                height: 10,
              ),
              ElevatedButton(
                  child: const Text(
                    "Alterar perfil",
                    style: TextStyle(
                        letterSpacing: 2.0, fontWeight: FontWeight.w300),
                  ),
                  onPressed: _changePerfil),
              const SizedBox(
                height: 15,
              ),
            ],
          ),
        )));
  }

  void _changePerfil() async {
    await showModalBottomSheet(
        isScrollControlled: true,
        context: context,
        builder: (BuildContext ctx) {
          return Padding(
              padding: EdgeInsets.only(
                  top: 20,
                  left: 20,
                  right: 20,
                  bottom: MediaQuery.of(ctx).viewInsets.bottom + 20),
              child: Form(
                key: _formKey,
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text("Informações do usuário",
                        style: TextStyle(
                          fontSize: 14.0,
                          color: Colors.green,
                          letterSpacing: 2.0,
                          fontWeight: FontWeight.w300,
                        )),
                    TextField(
                      controller: _nomeController,
                      decoration:
                          const InputDecoration(labelText: 'Nome do usuário'),
                    ),
                    TextField(
                      controller: _emailController,
                      decoration:
                          const InputDecoration(labelText: 'E-mail do usuário'),
                    ),
                    TextField(
                      controller: _telefoneController,
                      inputFormatters: [mascaraTelefone],
                      decoration: const InputDecoration(
                          labelText: 'Telefone do usuário'),
                    ),
                    TextField(
                      controller: _enderecoController,
                      decoration: const InputDecoration(labelText: 'Endereco'),
                    ),
                    TextField(
                      controller: _senha01Controller,
                      decoration: const InputDecoration(labelText: 'Senha'),
                    ),
                    TextField(
                      controller: _senha02Controller,
                      decoration:
                          const InputDecoration(labelText: 'Confirmar Senha'),
                    ),
                    ElevatedButton(
                      child: const Text('Alterar perfil'),
                      onPressed: () {
                        final FormState form = _formKey.currentState!;
                        if (form.validate()) {
                          PessoaModel pessoaModel = PessoaModel(
                              idPessoa: _idUsuario,
                              nome: _nomeController.text,
                              telefone: _telefoneController.text,
                              cpf: _cpfController.text,
                              email: _emailController.text,
                              password: _senha01Controller.text,
                              endereco: _enderecoController.text);
                          if (_senha01Controller.text != "" &&
                              _senha02Controller.text != "") {
                            if (_senha01Controller.text ==
                                _senha02Controller.text) {
                              PessoaModel.atualizaPessoa(pessoaModel)
                                  .then((value) => {
                                        if (value == "Error")
                                          {
                                            alert(
                                                "Erro ao atualizar perfil do usuário")
                                          }
                                        else
                                          {Navigator.of(context).pop()}
                                      });
                            } else {
                              alert("Confirmação de senha incorreta.");
                            }
                          } else {
                            alert("Senha em branco");
                          }
                        }
                      },
                    )
                  ],
                ),
              ));
        });
  }

  void alert(String text) {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            content: Text(text),
          );
        });
  }

  Future<String> get(String idUser, BuildContext context) async {
    _idUsuario = int.parse(idUser);
    final response =
        await http.get(Uri.parse('http://25.76.67.204:8080/pessoa/' + idUser));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String, dynamic>();
      _nomeController.text = parsed["nome"];
      _telefoneController.text = parsed["telefone"];
      _cpfController.text = parsed["cpf"];
      _emailController.text = parsed["email"];
      build(context);
      return "Response: " + response.statusCode.toString();
    } else {
      throw "Response: " + response.statusCode.toString();
    }
  }
}
