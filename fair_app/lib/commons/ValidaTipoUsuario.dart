import 'package:fair_app/pages/ResetPassword.dart';
import 'package:fair_app/pages/Signup.dart';
import 'package:flutter/material.dart';

import '../Main.dart';
import '../commons/Theme.dart';
import '../commons/widgets/LoginOption.dart';
import '../models/PessoaModel.dart';

class ValidaTipoUsuario extends StatefulWidget {
  const ValidaTipoUsuario({Key? key}) : super(key: key);

  @override
  _ValidaTipoUsuarioState createState() => _ValidaTipoUsuarioState();
}

class _ValidaTipoUsuarioState extends State<ValidaTipoUsuario> {
  @override
  Widget build(BuildContext context) {
    if (1 == 2) {
      Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => const MyHomePage(title: 'Fair')));
    }
    return Scaffold(
        body:Center( child:
        SingleChildScrollView(
            child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                          ElevatedButton(
                            child: const Text('Fair Usuário'),
                            onPressed: () {
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => const MyHomePage(
                                          title: 'Fair Empresa Usuário')));
                            },
                          ),
                          ElevatedButton(
                            child: const Text('Fair Dono'),
                            onPressed: () {
                              Navigator.push(
                                  context,
                                  MaterialPageRoute( // TODO Trocar Cor do Scaffold no Dono
                                      builder: (context) => const MyHomePage(
                                          title: 'Fair Empresa Dono')));
                            },
                          )
                  ]))));
  }
}
