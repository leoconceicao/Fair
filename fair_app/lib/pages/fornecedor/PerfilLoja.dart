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
    final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    _idLoja = int.parse(args.a['idLoja']);
    super.initState();
    LojaModel.findById(args.a['idLoja']).then((value) => {
      _nomeController.text = value['nome'],
      _cnpjController.text = value['cnpj'],
      _telefoneController.text = value['telefone'],
    });
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
  final TextEditingController _cidadeController = TextEditingController();
  final TextEditingController _estadoController = TextEditingController();
  final TextEditingController _bairroController = TextEditingController();
  late int _idLoja;
  @override
  Widget build(BuildContext context) {
    return Container(
        height: 500,
        child: Scaffold(
            body: SafeArea(
          child: Column(
            children: [
              Container(),
              /*Container(
                decoration: BoxDecoration(
                    image: DecorationImage(
                        image: NetworkImage(
                            "https://www.canaldapeca.com.br/blog/wp-content/uploads/2014/01/Curiosidades-Canal-da-Pe%C3%A7a-A-hist%C3%B3ria-do-Camaro.jpg"
                        ),
                        //fit: BoxFit.cover
                    )
                ),
                child: Container(
                  width: double.infinity,
                  height: 150,
                  child: Container(
                    alignment: Alignment(0.0,2.5),
                    child: CircleAvatar(
                      backgroundImage: NetworkImage(
                          "https://media-exp1.licdn.com/dms/image/C4E03AQEJCzI5uFl1Jg/profile-displayphoto-shrink_200_200/0/1600276692983?e=2147483647&v=beta&t=mBy4eOSgBKIJEQrlpLOGj2JT7P5RWLIVq5UpwvcUpOk"
                      ),
                      radius: 60.0,
                    ),
                  ),
                ),
              ),*/
              const SizedBox(
                height: 30,
              ),
              Text(
                _nomeController.text.trim() != "" ? _nomeController.text : "Nome da Loja" //Trocar pelo nome do usuário
                ,
                style: TextStyle(
                    fontSize: 20.0,
                    color: Colors.green,
                    letterSpacing: 2.0,
                    fontWeight: FontWeight.w400),
              ),
              const SizedBox(
                height: 10,
              ),
              Text(
                _cnpjController.text.trim() != "" ? _cnpjController.text : "CNPJ da Loja",
                style: TextStyle(
                  fontSize: 14.0,
                  color: Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300,
                ),
              ),
              const SizedBox(
                height: 10,
              ),
              const Text(
                "Blumenau, Santa Catarina" //Alterar para cidade pelo banco
                ,
                style: TextStyle(
                    fontSize: 18.0,
                    color: Colors.green,
                    letterSpacing: 2.0,
                    fontWeight: FontWeight.w300),
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
                  controller: _telefoneController,
                  inputFormatters: [mascaraTelefone],
                  keyboardType: TextInputType.number,
                  decoration: const InputDecoration(labelText: 'Telefone'),
                ),
                TextField(
                  controller: _cnpjController,
                  keyboardType: TextInputType.number,
                  inputFormatters: [mascaraCNPJ],
                  decoration: const InputDecoration(labelText: 'CNPJ'),
                ),
                TextField(
                  controller: _cidadeController,
                  decoration: const InputDecoration(labelText: 'Cidade'),
                ),
                TextField(
                  controller: _estadoController,
                  decoration: const InputDecoration(labelText: 'Estado'),
                ),
                ElevatedButton(
                  child: Text('Alterar perfil'),
                  onPressed: () {
                    _callChangePerfil;
                  },
                )
              ],
            ),
          );
        });
  }

  void _callChangePerfil() async {
    final FormState form = _formKey.currentState!;
    if (form.validate()) {
      LojaModel lojaModel = LojaModel(
          idLoja: _idLoja,
          nome: _nomeController.text,
          telefone: _telefoneController.text,
          cnpj: _cnpjController.text);
      LojaModel.atualizaLoja(lojaModel).then((value) => {
        if (value == "Error") {
          alert("Erro ao atualizar informações da loja")
        }
      });
    }
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
}
