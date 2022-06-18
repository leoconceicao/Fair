import 'dart:convert';

import 'package:fair_app/models/ProdutoModel.dart';
import 'package:flutter/material.dart';

import '../../commons/ScreenArguments.dart';
import '../../models/ProdutosPedidoModel.dart';

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
    );
  }
}

class ProdutosPedido extends StatefulWidget {
  const ProdutosPedido({Key? key}) : super(key: key);

  @override
  _ProdutosPedidoState createState() => _ProdutosPedidoState();
}

class _ProdutosPedidoState extends State<ProdutosPedido> {
  final bool _canShowButton = true;
  final TextEditingController _searchController = TextEditingController();
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _tipoController = TextEditingController();
  final TextEditingController _precoController = TextEditingController();
  final TextEditingController _validadeController = TextEditingController();
  final TextEditingController _pesoController = TextEditingController();
  final bool _hasNextPage = true;
  final bool _isFirstLoadRunning = false;
  bool _isLoadMoreRunning = false;
  bool search = false;

  late String idPedido;
  late String idProduto;

  void _loadMore() async {
    if (_hasNextPage == true &&
        _isFirstLoadRunning == false &&
        _isLoadMoreRunning == false &&
        _controller.position.extentAfter < 300) {
      setState(() {
        _isLoadMoreRunning = true;
      });
      setState(() {
        _isLoadMoreRunning = false;
      });
    }
  }

  late ScrollController _controller;

  @override
  void initState() {
    super.initState();
    _controller = ScrollController()..addListener(_loadMore);
  }

  @override
  void dispose() {
    _controller.removeListener(_loadMore);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    idPedido = args.value.split(" - ")[0];
    return SizedBox(
      child: Scaffold(
        body: search
            ? getFutureBuilderSearch(context, idPedido)
            : getFutureBuilder(context, idPedido),
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
        ),
        appBar: AppBar(
          title: Text(args.value),
          actions: const <Widget>[],
        ),
      ),
    );
  }

  Widget createListView(BuildContext context, AsyncSnapshot snapshot) {
    List<String> values =
        snapshot.data.runtimeType == String ? ['Teste'] : snapshot.data;
    return ListView.builder(
      itemCount: values.length,
      itemBuilder: (BuildContext context, int index) {
        return Column(
          children: <Widget>[
            ListTile(
              title: Text(values[index]),
              onTap: () {
                idProduto = values[index]
                    .toString()
                    .split(" - ")[0]
                    .replaceAll("#", "");
                _showProductInfo();
              },
            ),
            const Divider(
              height: 2.0,
            ),
          ],
        );
      },
    );
  }

  FutureBuilder getFutureBuilder(BuildContext context, String id) {
    return FutureBuilder(
      future: ProdutoPedidoModel.findProdutos(id),
      builder: (BuildContext context, AsyncSnapshot snapshot) {
        switch (snapshot.connectionState) {
          case ConnectionState.none:
          case ConnectionState.waiting:
            return const Text('loading...');
          default:
            if (snapshot.hasError) {
              return Text('Error: ${snapshot.error}');
            } else {
              return createListView(context, snapshot);
            }
        }
      },
    );
  }

  FutureBuilder getFutureBuilderSearch(BuildContext context, String id) {
    return FutureBuilder(
      future: ProdutoPedidoModel.getById(id),
      builder: (BuildContext context, AsyncSnapshot snapshot) {
        switch (snapshot.connectionState) {
          case ConnectionState.none:
          case ConnectionState.waiting:
            return const Text('loading...');
          default:
            if (snapshot.hasError) {
              return Text('Error: ${snapshot.error}');
            } else {
              return createListView(context, snapshot);
            }
        }
      },
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
                bottom: MediaQuery.of(ctx).viewInsets.bottom + 20),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TextField(
                  controller: _searchController,
                  decoration: const InputDecoration(
                      labelText:
                          'Digite aqui o produto a pesquisar entre os pedidos...'),
                ),
                ElevatedButton(
                  child: const Text('Pesquisar'),
                  onPressed: _callSearch,
                )
              ],
            ),
          );
        });
  }

  void _showProductInfo() async {
    ProdutoModel.findProductById(idProduto)
        .then((value) => {
          _nomeController.text = jsonDecode(value)["nome"],
          _tipoController.text = jsonDecode(value)["tipo"],
          _pesoController.text = jsonDecode(value)["peso"].toString(),
          _validadeController.text = jsonDecode(value)["validade"],
          _precoController.text = jsonDecode(value)["preco"].toString(),
        });
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
                const Text("Informações do Produto",
                    style: TextStyle(
                      fontSize: 14.0,
                      color: Colors.green,
                      letterSpacing: 2.0,
                      fontWeight: FontWeight.w300,
                    )),
                TextField(
                  controller: _nomeController,
                  readOnly: true,
                  decoration: const InputDecoration(labelText: 'Nome'),
                ),
                TextField(
                  controller: _tipoController,
                  readOnly: true,
                  decoration: const InputDecoration(labelText: 'Tipo'),
                ),
                TextField(
                  controller: _precoController,
                  readOnly: true,
                  keyboardType:
                      const TextInputType.numberWithOptions(decimal: true),
                  decoration: const InputDecoration(labelText: 'Preço'),
                ),
                TextField(
                  controller: _validadeController,
                  readOnly: true,
                  decoration: const InputDecoration(labelText: 'Validade'),
                ),
                TextField(
                  controller: _pesoController,
                  readOnly: true,
                  keyboardType:
                      const TextInputType.numberWithOptions(decimal: true),
                  decoration: const InputDecoration(labelText: 'Peso'),
                )
              ],
            ),
          );
        });
  }

  void _callSearch() async {
    setState(() {
      search = _searchController.text == "" ? false : true;
    });
  }
}
