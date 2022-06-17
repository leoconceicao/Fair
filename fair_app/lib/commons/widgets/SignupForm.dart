import 'package:fair_app/commons/widgets/Checkbox.dart';
import 'package:flutter/material.dart';
import '../../main.dart';
import '../Theme.dart';
import 'LoginOption.dart';

class SignUpForm extends StatefulWidget {
  const SignUpForm({Key? key}) : super(key: key);

  @override
  _SignUpFormState createState() => _SignUpFormState();
}

class _SignUpFormState extends State<SignUpForm> {
  bool _isObscure = true;
  bool _isCNPJ = false;
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _sobrenomeController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _enderecoController = TextEditingController();
  final TextEditingController _telefoneController = TextEditingController();
  final TextEditingController _cepController = TextEditingController();
  final TextEditingController _cpfController = TextEditingController();
  final TextEditingController _cidadeController = TextEditingController();
  final TextEditingController _estadoController = TextEditingController();
  final TextEditingController _nomeLojaController = TextEditingController();
  final TextEditingController _cnpjController = TextEditingController();
  final TextEditingController _cepLojaController = TextEditingController();
  final TextEditingController _telefoneLojaController = TextEditingController();
  final TextEditingController _enderecoLojaController = TextEditingController();
  final TextEditingController _cidadeLojaController = TextEditingController();
  final TextEditingController _estadoLojaController = TextEditingController();

  final TextEditingController _senhaController = TextEditingController();
  final TextEditingController _confirmaSenhaController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        buildInputForm('Nome', _nomeController, false),
        buildInputForm('Sobrenome', _sobrenomeController, false),
        buildInputForm('CPF', _cpfController, false),
        buildInputForm('Email', _emailController, false),
        buildInputForm('Telefone', _telefoneController, false),
        buildInputForm('Endereço', _enderecoController, false),
        buildInputForm('CEP de Localização', _cepController, false),
        buildInputForm('Cidade', _cidadeController, false),
        buildInputForm('Estado', _estadoController, false),
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
            buildInputForm('Nome da Loja', _nomeLojaController, false),
            buildInputForm('CNPJ', _cnpjController, false),
            buildInputForm('Telefone da Loja', _telefoneLojaController, false),
            buildInputForm('Endereço', _enderecoLojaController, false),
            buildInputForm('CEP da Loja', _cepLojaController, false),
            buildInputForm('Cidade', _cidadeLojaController, false),
            buildInputForm('Estado', _estadoLojaController, false),
          ]),
        ),
        buildInputForm('Senha', _senhaController, true),
        buildInputForm('Confirmar Senha', _confirmaSenhaController, true),
        const SizedBox(
          height: 20,
        ),
        const Padding(
          padding: kDefaultPadding,
          child: CheckBox('Eu concordo com os Termos e Serviços'),
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
    );
  }

  Padding buildInputForm(
      String hint, TextEditingController textEditingController, bool pass) {
    return Padding(
        padding: const EdgeInsets.symmetric(vertical: 5),
        child: TextFormField(
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

  void _validarCadastro(context) {
    if (1 == 1) {
      Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => const MyHomePage(title: 'Fair')));
    }
  }
}
