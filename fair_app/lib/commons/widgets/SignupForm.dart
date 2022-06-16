import 'package:flutter/material.dart';

import '../Theme.dart';

class SignUpForm extends StatefulWidget {
  const SignUpForm({Key? key}) : super(key: key);

  @override
  _SignUpFormState createState() => _SignUpFormState();
}

class _SignUpFormState extends State<SignUpForm> {
  bool _isObscure = true;
  bool _isCNPJ = false;
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        buildInputForm('Nome', false),
        buildInputForm('Sobrenome', false),
        buildInputForm('Email', false),
        buildInputForm('Telefone', false),
        buildInputForm('Endereço', false),
        buildInputForm('CEP de Localização', false),
        buildInputForm('Cidade', false),
        buildInputForm('Estado', false),
        CheckboxListTile(
          title: const Text('É um fornecedor? Cadastre as informações da sua empresa!'),
          value: _isCNPJ,
          onChanged: (value) {
            setState(() {
              _isCNPJ = value!;
            });
          },
        ),
        Visibility(
          visible: _isCNPJ, // bool
          child: Column(
            children:[
              buildInputForm('Nome da Loja', false),
              buildInputForm('CNPJ', false),
              buildInputForm('Telefone da Loja', false),
              buildInputForm('Endereço', false),
              buildInputForm('CEP de Localização', false),
              buildInputForm('Telefone', false),
              buildInputForm('Cidade', false),
              buildInputForm('Estado', false),
            ]
          ),
        ),
        buildInputForm('Senha', true),
        buildInputForm('Confirmar Senha', true),
      ],
    );
  }

  Padding buildInputForm(String hint, bool pass) {
    return Padding(
        padding: const EdgeInsets.symmetric(vertical: 5),
        child: TextFormField(
          obscureText: pass ? _isObscure : false,
          decoration: InputDecoration(
            hintText: hint,
            hintStyle: const TextStyle(color: Colors.black),
            focusedBorder: const UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.black)),
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
                            color: Colors.green,
                          )
                        : const Icon(
                            Icons.visibility,
                            color: Colors.green,
                          ))
                : null,
          ),
        ));
  }
}
