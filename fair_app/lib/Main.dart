import 'package:fair_app/commons/ScreenArguments.dart';
import 'package:fair_app/commons/ValidaTipoUsuario.dart';
import 'package:fair_app/pages/cliente/HomeCliente.dart';
import 'package:fair_app/pages/cliente/Pedidos.dart';
import 'package:fair_app/pages/cliente/PerfilCliente.dart';
import 'package:fair_app/pages/cliente/ProdutosLoja.dart';
import 'package:fair_app/pages/shared/ProdutosPedido.dart';
import 'package:fair_app/pages/fornecedor/HomeFornecedor.dart';
import 'package:fair_app/pages/fornecedor/PerfilLoja.dart';
import 'package:fair_app/pages/fornecedor/VendasFornecedor.dart';
import 'package:fair_app/pages/startup/Login.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      initialRoute: '/login',
      // home: const MyHomePage(title: 'Fair'),
      routes: {
        '/maincliente': (context) => const MyHomePage(title: 'Fair'),
        '/maindono': (context) => const MyHomePage(title: 'Fair Para Donos'),
        '/login': (context) => const LogInScreen(),
        '/home': (context) => const Home(),
        '/pedidos': (context) => const Pedidos(),
        '/produtospedido': (context) => const ProdutosPedido(),
        '/produtosloja': (context) => const ProdutosLoja(),
        '/validatipousuario': (context) => const ValidaTipoUsuario(),
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class NewPageScreen extends StatelessWidget {
  final String texto;

  const NewPageScreen(this.texto, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(texto),
    );
  }
}

class _MyHomePageState extends State<MyHomePage> {
  bool _isCNPJ = false;
  int _indiceAtual = 0;
  bool _canShowButton = true;
  List<Widget> _telas = [const Home(), const Pedidos(), const Perfil()];


  void onTabTapped(int index) {
    if (index == 2) {
      hideWidget();
    } else if (([0, 1].any((index) => index > -1)) && !_canShowButton) {
      hideWidget();
    }

    setState(() {
      _indiceAtual = index;
    });
  }

  void hideWidget() {
    setState(() {
      _canShowButton = !_canShowButton;
    });
  }

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    _isCNPJ = args.value == 'S' ? true : false;
    _telas = !_isCNPJ ? [const Home(), const Pedidos(), const Perfil()] : [const HomeFornecedor(), const VendasFornecedor(), const PerfilLoja()];
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        automaticallyImplyLeading: false,
        centerTitle: true,
      ),
      body: Center(
          child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          _telas[_indiceAtual],
        ],
      )),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _indiceAtual,
        onTap: onTabTapped,
        items: <BottomNavigationBarItem>[
          BottomNavigationBarItem(icon: const Icon(Icons.home), label: !_isCNPJ ? 'Início' : 'Produtos'),
          BottomNavigationBarItem(
              icon: const Icon(Icons.shopping_basket), label: !_isCNPJ ? 'Meus pedidos' : 'Vendas'),
          BottomNavigationBarItem(icon: const Icon(Icons.person), label: !_isCNPJ ? 'Perfil' : 'Loja'),
        ],
      ),
    );
  }
}
