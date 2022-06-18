import 'dart:collection';
import 'dart:convert';

import 'package:fair_app/models/FuncionariosModel.dart';
import 'package:fair_app/pages/startup/ResetPassword.dart';
import 'package:fair_app/pages/startup/Signup.dart';
import 'package:flutter/material.dart';

import '../../commons/ScreenArguments.dart';
import '../../commons/Theme.dart';
import '../../commons/widgets/LoginOption.dart';
import '../../models/PessoaModel.dart';

class LogInScreen extends StatefulWidget {
  const LogInScreen({Key? key}) : super(key: key);

  @override
  _LogInScreenState createState() => _LogInScreenState();
}

class _LogInScreenState extends State<LogInScreen> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  bool _isObscure = true;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: kDefaultPadding,
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const SizedBox(
                height: 120,
              ),
              Text(
                'Bem-vindo(a) ao Fair',
                style: titleText,
              ),
              const SizedBox(
                height: 5,
              ),
              Row(
                children: [
                  Text(
                    'Novo no aplicativo?',
                    style: subTitle,
                  ),
                  const SizedBox(
                    width: 5,
                  ),
                  GestureDetector(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => SignUpScreen(),
                        ),
                      );
                    },
                    child: Text(
                      'Inscreva-se já!',
                      style: textButton.copyWith(
                        decoration: TextDecoration.underline,
                        decorationThickness: 1,
                      ),
                    ),
                  ),
                ],
              ),
              const SizedBox(
                height: 10,
              ),
              Column(
                children: [
                  buildInputForm('E-mail', _emailController, false),
                  buildInputForm('Senha', _passwordController, true),
                ],
              ),
              const SizedBox(
                height: 20,
              ),
              GestureDetector(
                onTap: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => const ResetPasswordScreen()));
                },
                child: const Text(
                  'Esqueci minha senha',
                  style: TextStyle(
                    color: kSecondaryColor,
                    fontSize: 14,
                    decoration: TextDecoration.underline,
                    decorationThickness: 1,
                  ),
                ),
              ),
              const SizedBox(
                height: 20,
              ),
              ElevatedButton(
                child: const Text('Login'),
                onPressed: () {
                  _validarLogin(context);
                },
              ),
              const SizedBox(
                height: 20,
              ),
              Text(
                'Ou realize seu login com:',
                style: subTitle.copyWith(color: kBlackColor),
              ),
              const SizedBox(
                height: 20,
              ),
              const LoginOption(),
            ],
          ),
        ),
      ),
    );
  }

  Padding buildInputForm(
      String label, TextEditingController controller, bool pass) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 5),
      child: TextFormField(
        controller: controller,
        obscureText: pass ? _isObscure : false,
        decoration: InputDecoration(
            labelText: label,
            labelStyle: const TextStyle(
              color: kTextFieldColor,
            ),
            focusedBorder: const UnderlineInputBorder(
              borderSide: BorderSide(color: kPrimaryColor),
            ),
            suffixIcon: pass
                ? IconButton(
                    onPressed: () {
                      setState(() {
                        _isObscure = !_isObscure;
                      });
                    },
                    icon: _isObscure
                        ? const Icon(
                            Icons.visibility_off,
                            color: kTextFieldColor,
                          )
                        : const Icon(
                            Icons.visibility,
                            color: kPrimaryColor,
                          ),
                  )
                : null),
      ),
    );
  }

  void alert() {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return const AlertDialog(
            content: Text("Email ou senha incorreta"),
          );
        });
  }

  void _validarLogin(context) {
    HashMap args = HashMap();
    String email = _emailController.text;
    String password = _passwordController.text;
    if (email == "" || password == "") {
      alert();
    } else {
      try {
        PessoaModel.findByEmail(email, password).then((value) => {
              if (value["email"].toString() == value["emailDb"].toString() &&
                  value["password"].toString() ==
                      value["passwordDb"].toString())
                {
                  if (value["idPessoa"] != null)
                    {
                      FuncionarioModel.findByIdPessoa(value["idPessoa"])
                          .then((f) => {
                                if (f == "null")
                                  {
                                    args["userId"] = value["idPessoa"],
                                    Navigator.pushNamed(context, '/maincliente',
                                        arguments: ScreenArguments(
                                            'isCnpj', 'N', args)),
                                  }
                                else
                                  {
                                    args["idLoja"] = jsonDecode(f)
                                            .cast<String, dynamic>()["fkLoja"]
                                        ["idLoja"],
                                    Navigator.pushNamed(
                                        context, '/validatipousuario',
                                        arguments: ScreenArguments(
                                            'idLoja',
                                            args["idLoja"].toString(),
                                            HashMap()))
                                  }
                              }),
                    } else {
                    alert()
                  }
                }
              else
                {alert()}
            });
      } on Exception {
        alert();
      }
    }
  }
}
