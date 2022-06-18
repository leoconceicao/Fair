import 'dart:collection';

import 'package:fair_app/commons/ScreenArguments.dart';
import 'package:flutter/material.dart';

class ValidaTipoUsuario extends StatefulWidget {
  const ValidaTipoUsuario({Key? key}) : super(key: key);

  @override
  _ValidaTipoUsuarioState createState() => _ValidaTipoUsuarioState();
}

class _ValidaTipoUsuarioState extends State<ValidaTipoUsuario> {
  HashMap a = HashMap();

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    a["idLoja"] = args.value;
    return Scaffold(
        body: Center(
            child: SingleChildScrollView(
                child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
          ElevatedButton(
            child: const Text('Fair'),
            onPressed: () {
              Navigator.pushNamed(context, '/maincliente',
                  arguments: ScreenArguments('isCnpj', 'N', HashMap()));
            },
          ),
          ElevatedButton(
            child: const Text('Fair Para Dono'),
            onPressed: () {
              Navigator.pushNamed(context, '/maindono',
                  arguments: ScreenArguments('isCnpj', 'S', a));
            },
          )
        ]))));
  }
}
