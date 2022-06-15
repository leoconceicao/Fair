import 'package:flutter/material.dart';
import 'package:material_floating_search_bar/material_floating_search_bar.dart';
import 'pages/home.dart';
import 'pages/Pedidos.dart';
import 'pages/Perfil.dart';

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
      home: const MyHomePage(title: 'Fair'),
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

  NewPageScreen(this.texto);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: Text(texto),
      ),
    );
  }
}

class _MyHomePageState extends State<MyHomePage> {
  int _indiceAtual = 0;
  bool _canShowButton = true;
  final TextEditingController _searchController = TextEditingController();
  final List<Widget> _telas = [
    Home(),
    Pedidos(),
    Perfil()
  ];

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
      floatingActionButton: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        children: <Widget>[
          Visibility(
            visible: _canShowButton, // bool
            child: FloatingActionButton(
              onPressed: _searchProducts,
              tooltip: 'Pesquisar',
              child: const Icon(Icons.search),
            ), // widget to show/hide
          ),
          const SizedBox(
            width: 10.0,
          ),
        ],
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  void _searchProducts() async {
    _searchController.text = '';
    await showModalBottomSheet(
        isScrollControlled: true,
        context: context,
        builder: (BuildContext ctx) {
          return Padding(
            padding: EdgeInsets.only(
                top: 20,
                left: 20,
                right: 20,
                // prevent the soft keyboard from covering text fields
                bottom: MediaQuery.of(ctx).viewInsets.bottom + 20),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TextField(
                  controller: _searchController,
                  decoration: InputDecoration(
                      labelText: _indiceAtual == 0
                          ? 'Digite aqui o produto a pesquisar...'
                          : 'Digite aqui o produto a pesquisar entre os pedidos...'),
                ),
                ElevatedButton(
                  child: Text('Pesquisar'),
                  onPressed: _callSearch,
                )
              ],
            ),
          );
        });
  }
}
