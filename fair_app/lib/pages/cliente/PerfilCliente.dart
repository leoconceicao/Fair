import 'dart:async';
import 'dart:convert';

import 'package:fair_app/commons/ScreenArguments.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class Perfil extends StatefulWidget {
  const Perfil({Key? key}) : super(key: key);

  @override
  _PerfilState createState() => _PerfilState();
}

class _PerfilState extends State<Perfil> {
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _telefoneController = TextEditingController();
  final TextEditingController _cpfController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _cidadeController = TextEditingController();
  final TextEditingController _estadoController = TextEditingController();
  final TextEditingController _bairroController = TextEditingController();
  final TextEditingController _cepController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    get(args.a["userId"].toString());
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
              const Text(
                "Gustavo Bulhmann" //Trocar pelo nome do usuário
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
              const Text(
                "Email do usuário",
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
                  controller: _telefoneController,
                  decoration:
                      const InputDecoration(labelText: 'Telefone do usuário'),
                ),
                TextField(
                  controller: _cpfController,
                  decoration:
                      const InputDecoration(labelText: 'CPF do usuário'),
                ),
                TextField(
                  controller: _emailController,
                  decoration:
                      const InputDecoration(labelText: 'E-mail do usuário'),
                ),
                const ElevatedButton(
                  child: Text('Alterar perfil'),
                  onPressed: null,
                )
              ],
            ),
          );
        });
  }

  Future<String> get(String idUser) async {
    final response =
        await http.get(Uri.parse('http://25.76.67.204:8080/pessoa/' + idUser));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String, dynamic>();
      _nomeController.text = parsed["nome"];
      _telefoneController.text = parsed["telefone"];
      _cpfController.text = parsed["cpf"];
      _emailController.text = parsed["email"];
      return "Response: " + response.statusCode.toString();;
    } else {
      throw "Response: " + response.statusCode.toString();
    }
  }
}
