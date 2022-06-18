import 'package:fair_app/commons/ScreenArguments.dart';
import 'package:flutter/material.dart';

class ValidaTipoUsuario extends StatefulWidget {
  const ValidaTipoUsuario({Key? key}) : super(key: key);

  @override
  _ValidaTipoUsuarioState createState() => _ValidaTipoUsuarioState();
}

class _ValidaTipoUsuarioState extends State<ValidaTipoUsuario> {
  @override
  Widget build(BuildContext context) {
    if (1 == 2) {
      Navigator.pushNamed(context, '/maincliente',
          arguments: ScreenArguments('isCnpj', 'N'));
    }
    return Scaffold(
        body: Center(
            child: SingleChildScrollView(
                child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
          ElevatedButton(
            child: const Text('Fair Para Funcionários'),
            onPressed: () {
              Navigator.pushNamed(context, '/mainfuncionario',
                  arguments: ScreenArguments('isCnpj', 'N'));
            },
          ),
          ElevatedButton(
            child: const Text('Fair Para Dono'),
            onPressed: () {
              Navigator.pushNamed(context, '/maindono',
                  arguments: ScreenArguments('isCnpj', 'S'));
            },
          )
        ]))));
  }
}
