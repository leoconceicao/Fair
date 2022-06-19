import 'dart:async';
import 'dart:collection';
import 'dart:convert';

import 'package:fair_app/commons/ScreenArguments.dart';
import 'package:fair_app/models/LojaModel.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mask_text_input_formatter/mask_text_input_formatter.dart';

class PerfilLoja extends StatefulWidget {
  const PerfilLoja({Key? key}) : super(key: key);

  @override
  _PerfilLojaState createState() => _PerfilLojaState();
}

class _PerfilLojaState extends State<PerfilLoja> {
  @override
  void initState() {
    // TODO: implement initState
    // final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    // _idLoja = int.parse(args.a['idLoja']);
    // super.initState();
    // LojaModel.findById(args.a['idLoja']).then((value) => {
    //   _nomeController.text = value['nome'],
    //   _cnpjController.text = value['cnpj'],
    //   _telefoneController.text = value['telefone'],
    // });
  }
  final mascaraTelefone = MaskTextInputFormatter(
      mask: '(##) #####-####',
      filter: {"#": RegExp(r'[0-9]')},
      type: MaskAutoCompletionType.lazy);

  final mascaraCNPJ = MaskTextInputFormatter(
      mask: '##.###.###/####-##',
      filter: {"#": RegExp(r'[0-9]')},
      type: MaskAutoCompletionType.lazy);

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _telefoneController = TextEditingController();
  final TextEditingController _cnpjController = TextEditingController();
  final TextEditingController _enderecoController = TextEditingController();
  late int _idLoja;
  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    if (_nomeController.text == "") {
      get(args.a["idLoja"].toString(), context);
    }
    return Container(
        height: 500,
        child: Scaffold(
            body: SafeArea(
          child: Column(
            children: [
              Container(),
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
                  labelText: 'Nome Lojas',
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
                  labelText: 'Endereço Loja',
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
              const SizedBox(
                height: 10,
              ),
              ElevatedButton(
                  child: const Text(
                    "Alterar informações loja",
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
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const Text("Informações da Loja",
                    style: TextStyle(
                      fontSize: 14.0,
                      color: Colors.green,
                      letterSpacing: 2.0,
                      fontWeight: FontWeight.w300,
                    )),
                TextField(
                  controller: _nomeController,
                  decoration: const InputDecoration(labelText: 'Nome'),
                ),
                TextField(
                  controller: _enderecoController,
                  decoration: const InputDecoration(labelText: 'Endereço'),
                ),
                TextField(
                  controller: _telefoneController,
                  inputFormatters: [mascaraTelefone],
                  decoration: const InputDecoration(labelText: 'Telefone'),
                ),
                ElevatedButton(
                  child: const Text('Alterar perfil'),
                  onPressed: () {
                      LojaModel lojaModel = LojaModel(
                          idLoja: _idLoja,
                          nome: _nomeController.text,
                          telefone: _telefoneController.text,
                          cnpj: _cnpjController.text,
                          endereco: _enderecoController.text);
                      LojaModel.atualizaLoja(lojaModel).then((value) => {
                            if (value == "Error")
                              {alert("Erro ao atualizar informações da loja")}
                          });
                      Navigator.of(context).pop();
                  },
                )
              ],
            ),
          );
        });
  }

  void _callChangePerfil() async {}

  void alert(String text) {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            content: Text(text),
          );
        });
  }

  Future<String> get(String idLoja, BuildContext context) async {
    _idLoja = int.parse(idLoja);
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/loja/findById/' + idLoja));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String, dynamic>();
      _nomeController.text = parsed["nome"];
      _telefoneController.text = parsed["telefone"];
      _enderecoController.text = parsed["endereco"];
      build(context);
      return "Response: " + response.statusCode.toString();
    } else {
      throw "Response: " + response.statusCode.toString();
    }
  }
}
