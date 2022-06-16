import 'package:fair_app/pages/Pedidos.dart';
import 'package:fair_app/pages/ProdutosPedido.dart';
import 'package:flutter/material.dart';

import '../pages/Home.dart';
import '../pages/login.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(fontFamily: 'Poppins'),
      home: LogInScreen(),
      routes: {
        '/home': (context) => const Home(),
        '/pedidos': (context) => const Pedidos(),
        '/produtospedido': (context) => const ProdutosPedido(),
      },
    );
  }
}
