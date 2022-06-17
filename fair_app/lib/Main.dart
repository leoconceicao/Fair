import 'package:fair_app/pages/Home.dart';
import 'package:fair_app/pages/Login.dart';
import 'package:fair_app/pages/Vendedores.dart';
import 'package:flutter/material.dart';

import 'pages/Home.dart';
import 'pages/Pedidos.dart';
import 'pages/Perfil.dart';
import 'pages/ProdutosPedido.dart';


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
        '/login': (context) => const LogInScreen(),
        '/home': (context) => const Home(),
        '/pedidos': (context) => const Pedidos(),
        '/produtospedido': (context) => const ProdutosPedido(),
        '/vendedores': (context) => const Vendedores(),
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
  int _indiceAtual = 0;
  bool _canShowButton = true;
  final TextEditingController _searchController = TextEditingController();
  final List<Widget> _telas = [const Home(), const Pedidos(), const Perfil()];

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

  void _callSearch() async {}
  @override
  Widget build(BuildContext context) {
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
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Início'),
          BottomNavigationBarItem(
              icon: Icon(Icons.shopping_basket), label: 'Meus pedidos'),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: 'Perfil'),
        ],
      ),
    );
  }
}
