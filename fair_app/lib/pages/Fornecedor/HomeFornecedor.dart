import 'dart:collection';
import 'dart:convert';

import 'package:flutter/material.dart';

import '../../commons/ScreenArguments.dart';
import '../../models/ProdutoModel.dart';
import '../../models/ProdutosLojaModel.dart';

class HomeFornecedor extends StatefulWidget {
  const HomeFornecedor({Key? key}) : super(key: key);

  @override
  _HomeFornecedorState createState() => _HomeFornecedorState();
}

class _HomeFornecedorState extends State<HomeFornecedor> {
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

  String idLoja = "";

  List<String> productIds = [];

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
    idLoja = args.a["idLoja"];
    return SizedBox(
      height: 500,
      child: Scaffold(
        body: search
            ? getFutureBuilderSearch(context, args.a["idLoja"])
            : getFutureBuilder(context, args.a["idLoja"]),
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
              width: 8.0,
            ),
            Row(children: [
              FloatingActionButton(
                onPressed: _addProduct,
                tooltip: 'Adicionar produto',
                child: const Icon(Icons.add),
              )
            ])
          ],
        ),
      ),
    );
  }

  Widget createListView(BuildContext context, AsyncSnapshot snapshot) {
    List<String> values = snapshot.data;
    return ListView.builder(
      itemCount: values.length,
      itemBuilder: (BuildContext context, int index) {
        return Column(
          children: <Widget>[
            ListTile(
              title: Text(values[index].toString().split(" - ")[1]),
              onTap: () {
                Navigator.pushNamed(context, '/produtosloja',
                    arguments: ScreenArguments(
                        values[index].toString().split(" - ")[0],
                        values[index].toString().split(" - ")[1],
                        HashMap()));
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

  FutureBuilder getFutureBuilder(BuildContext context, String idLoja) {
    return FutureBuilder(
      future: ProdutosLojaModel.findProdutosByLoja(idLoja),
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

  FutureBuilder getFutureBuilderSearch(BuildContext context, String idLoja) {
    return FutureBuilder(
      future: ProdutosLojaModel.findProdutosByLoja(_searchController.text),
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
                          'Digite aqui para pesquisar entre os produtos...'),
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

  void _addProduct() async {
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
                  controller: _nomeController,
                  decoration:
                      const InputDecoration(labelText: 'Nome do produto'),
                ),
                TextField(
                  controller: _tipoController,
                  decoration: const InputDecoration(
                      labelText: 'Tipo de produto a ser vendido'),
                ),
                TextField(
                  controller: _precoController,
                  keyboardType:
                      const TextInputType.numberWithOptions(decimal: true),
                  decoration:
                      const InputDecoration(labelText: 'Preço do produto'),
                ),
                TextField(
                  controller: _validadeController,
                  decoration: const InputDecoration(
                      labelText:
                          'Validade do produto conforme (Ex. 01/01/2021)'),
                ),
                TextField(
                  controller: _pesoController,
                  keyboardType:
                      const TextInputType.numberWithOptions(decimal: true),
                  decoration:
                      const InputDecoration(labelText: 'Peso do produto'),
                ),
                ElevatedButton(
                  child: const Text('Adicionar produto'),
                  onPressed: (){
                    _callAdd();
                  }
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

  void _callAdd() async {
    ProdutoModel p = ProdutoModel(
        idProduto: 0,
        nome: _nomeController.text,
        tipo: _tipoController.text,
        preco: double.parse(_precoController.text),
        foto: "null",
        validade: _validadeController.text,
        peso: double.parse(_pesoController.text));

    ProdutoModel.addProduto(p).then((value) => {
      jsonDecode(value),
      ProdutosLojaModel.addProdutoLoja(jsonDecode(value)["idProduto"], 1, jsonDecode(value)["preco"])
    });
  }
}
