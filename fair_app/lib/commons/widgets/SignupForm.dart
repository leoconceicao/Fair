import 'dart:collection';
import 'dart:convert';

import 'package:fair_app/commons/widgets/Checkbox.dart';
import 'package:fair_app/models/FuncionariosModel.dart';
import 'package:fair_app/models/LojaModel.dart';
import 'package:fair_app/models/PessoaModel.dart';
import 'package:flutter/material.dart';

import 'package:fair_app/commons/ScreenArguments.dart';
import 'package:fair_app/commons/Theme.dart';
import 'package:mask_text_input_formatter/mask_text_input_formatter.dart';
import 'package:validadores/Validador.dart';

import 'package:fair_app/commons/widgets/LoginOption.dart';

class SignUpForm extends StatefulWidget {
  const SignUpForm({Key? key}) : super(key: key);

  @override
  _SignUpFormState createState() => _SignUpFormState();
}

class _SignUpFormState extends State<SignUpForm> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  final mascaraTelefone = MaskTextInputFormatter(
      mask: '(##) #####-####',
      filter: {"#": RegExp(r'[0-9]')},
      type: MaskAutoCompletionType.lazy);

  final mascaraCPF = MaskTextInputFormatter(
      mask: '###.###.###-##',
      filter: {"#": RegExp(r'[0-9]')},
      type: MaskAutoCompletionType.lazy);

  final mascaraCNPJ = MaskTextInputFormatter(
      mask: '##.###.###/####-##',
      filter: {"#": RegExp(r'[0-9]')},
      type: MaskAutoCompletionType.lazy);
  bool _isObscure = true;
  bool _isCNPJ = false;
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _telefoneController = TextEditingController();
  final TextEditingController _cpfController = TextEditingController();
  final TextEditingController _nomeLojaController = TextEditingController();
  final TextEditingController _cnpjController = TextEditingController();
  final TextEditingController _telefoneLojaController = TextEditingController();

  final TextEditingController _senhaController = TextEditingController();
  final TextEditingController _confirmaSenhaController =
      TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
        child:Column(
      children: [
        buildInputForm('Nome', _nomeController, false, TextInputType.text),
        buildInputForm('Email', _emailController, false, TextInputType.emailAddress),
        buildInputFormValidadorComMascara('CPF', _cpfController, false, mascaraCPF, TextInputType.number, Validar.CPF, 'CPF inválido'),
        buildInputFormComMascara('Telefone', _telefoneController, false, mascaraTelefone),
        buildInputForm('Senha', _senhaController, true, TextInputType.text),
        buildInputForm('Confirmar Senha', _confirmaSenhaController, true, TextInputType.text),
        CheckboxListTile(
          title: const Text(
              'É um fornecedor? Cadastre as informações da sua empresa!'),
          value: _isCNPJ,
          onChanged: (value) {
            setState(() {
              _isCNPJ = value!;
            });
          },
        ),
        Visibility(
          visible: _isCNPJ, // bool
          child: Column(children: [
            buildInputForm('Nome da Loja', _nomeLojaController, false, TextInputType.text),
            buildInputFormValidadorComMascara('CNPJ', _cnpjController, false, mascaraCNPJ, TextInputType.number, Validar.CNPJ, 'CNPJ inválido'),
            buildInputFormComMascara('Telefone da Loja', _telefoneLojaController, false, mascaraTelefone),
          ]),
        ),
        const SizedBox(
          height: 20,
        ),
        const SizedBox(
          height: 20,
        ),
        const SizedBox(
          height: 20,
        ),
        Container(
          alignment: Alignment.center,
          height: MediaQuery.of(context).size.height * 0.08,
          width: double.infinity,
          child: ElevatedButton(
            child: const Text('Cadastrar Usuário'),
            onPressed: () {
              _validarCadastro(context);
            },
          ),
        ),
        const SizedBox(
          height: 20,
        ),
        Padding(
          padding: kDefaultPadding,
          child: Text(
            'Ou faça o login com:',
            style: subTitle.copyWith(color: kBlackColor),
          ),
        ),
        const SizedBox(
          height: 20,
        ),
        const Padding(
          padding: kDefaultPadding,
          child: LoginOption(),
        ),
        const SizedBox(
          height: 20,
        ),
      ],
    ));
  }

  Padding buildInputForm(
      String hint, TextEditingController textEditingController, bool pass, TextInputType tipoTeclado) {
    return Padding(
        padding: const EdgeInsets.symmetric(vertical: 5),
        child: TextFormField(
          keyboardType: tipoTeclado != null ? tipoTeclado : TextInputType.text,
          controller: textEditingController,
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

  Padding buildInputFormComMascara(
      String hint, TextEditingController textEditingController, bool pass, MaskTextInputFormatter tipoMascara) {
    return Padding(
        padding: const EdgeInsets.symmetric(vertical: 5),
        child: TextFormField(
          inputFormatters: [tipoMascara],
          controller: textEditingController,
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

  Padding buildInputFormValidadorComMascara(
      String hint,
      TextEditingController textEditingController,
      bool pass,
      MaskTextInputFormatter mascara,
      TextInputType tipoTeclado,
      Validar tipoValidacao,
      String mensagem) {
    return Padding(
        padding: const EdgeInsets.symmetric(vertical: 5),
        child: TextFormField(
          inputFormatters: [mascara],
          keyboardType: tipoTeclado,
          validator: (value) {
            // Aqui entram as validações
            return Validador()
                .add(tipoValidacao, msg: mensagem)
                .add(Validar.OBRIGATORIO, msg: 'Campo obrigatório')
                .valido(value, clearNoNumber: true);
          },
          controller: textEditingController,
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

  Padding buildInputFormValidador(
      String hint,
      TextEditingController textEditingController,
      bool pass,
      TextInputType tipoTeclado,
      Validar tipoValidacao,
      String mensagem) {
    return Padding(
        padding: const EdgeInsets.symmetric(vertical: 5),
        child: TextFormField(
          keyboardType: tipoTeclado,
          validator: (value) {
            // Aqui entram as validações
            return Validador()
                .add(tipoValidacao, msg: mensagem)
                .add(Validar.OBRIGATORIO, msg: 'Campo obrigatório')
                .valido(value, clearNoNumber: true);
          },
          controller: textEditingController,
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

  void alert(String text) {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            content: Text(text),
          );
        });
  }

  void _validarCadastro(context) {
    final FormState form = _formKey.currentState!;
    if (form.validate()) {
      PessoaModel pessoaModel = PessoaModel(
          idPessoa: 0,
          nome: _nomeController.text,
          telefone: _telefoneController.text,
          cpf: _cpfController.text,
          email: _emailController.text,
          password: _senhaController.text,
          endereco: "");

      late FuncionarioModel funcionarioModel = FuncionarioModel(0, "F", 0, 0);

      bool success = true;
      if (_isCNPJ) {
        LojaModel lojaModel = LojaModel(
            idLoja: 0,
            nome: _nomeLojaController.text,
            cnpj: _cnpjController.text,
            telefone: _telefoneLojaController.text);
        LojaModel.addLoja(lojaModel).then((value) =>
        {
          if (value == "Error")
            {alert("Erro ao cadastrar loja"), success = false}
          else
            {
              addPessoa(success, pessoaModel, funcionarioModel,
                  jsonDecode(value).cast<String, dynamic>()["idLoja"])
            }
        });
        funcionarioModel.cargo = "D";
      } else {
        addPessoa(success, pessoaModel, funcionarioModel, 0);
      }
    }
  }

  void addPessoa(bool success, PessoaModel pessoaModel,
      FuncionarioModel funcionarioModel, int idLoja) {
    // ignore: prefer_typing_uninitialized_variables
    var resultPessoa;
    PessoaModel.addPessoa(pessoaModel).then((value) => {
          if (value != "Error" && success)
            {
              if (_isCNPJ)
                {
                  resultPessoa = jsonDecode(value).cast<String, dynamic>(),
                  funcionarioModel.fkPessoa = resultPessoa["idPessoa"],
                  funcionarioModel.fkLoja = idLoja,
                  FuncionarioModel.addFuncionario(funcionarioModel)
                      .then((value) => {
                            if (value == "Error")
                              {alert("Erro ao cadastrar funcionario")}
                          }),
                  Navigator.pushNamed(context, '/validatipousuario',
                      arguments: ScreenArguments(
                          'idLoja', idLoja.toString(), HashMap()))
                }
              else
                {
                  Navigator.pushNamed(context, '/maincliente',
                      arguments: ScreenArguments('isCnpj', 'N', HashMap())),
                },
            }
          else
            {alert("Erro ao cadastrar pessoa")}
        });
  }
}
