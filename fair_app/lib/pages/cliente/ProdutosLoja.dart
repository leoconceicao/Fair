import 'dart:convert';

import 'package:fair_app/commons/ScreenArguments.dart';
import 'package:fair_app/models/PedidoModel.dart';
import 'package:fair_app/models/ProdutoModel.dart';
import 'package:fair_app/models/ProdutosLojaModel.dart';
import 'package:fair_app/models/ProdutosPedidoModel.dart';
import 'package:fair_app/pages/shared/ProdutosPedido.dart';
import 'package:flutter/material.dart';

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

class ProdutosLoja extends StatefulWidget {
  const ProdutosLoja({Key? key}) : super(key: key);

  @override
  _ProdutosPedidoState createState() => _ProdutosPedidoState();
}

class _ProdutosPedidoState extends State<ProdutosLoja> {
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _tipoController = TextEditingController();
  final TextEditingController _precoController = TextEditingController();
  final TextEditingController _validadeController = TextEditingController();
  final TextEditingController _pesoController = TextEditingController();
  final TextEditingController _pesoEscolhidoController =
      TextEditingController();
  final TextEditingController _quantidadeController = TextEditingController();
  final TextEditingController _dataEntregaController = TextEditingController();
  final TextEditingController _periodicidadeController =
      TextEditingController();
  final TextEditingController _numeroEntregasController =
      TextEditingController();
  final bool _canShowButton = true;
  final TextEditingController _searchController = TextEditingController();
  final TextEditingController titleController = TextEditingController();
  final bool _hasNextPage = true;
  final bool _isFirstLoadRunning = false;
  bool _isLoadMoreRunning = false;
  bool search = false;

  late String idProduto;
  late String idLoja;
  late int idUser;
  late int quantidade;
  late String preco;

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
    idUser = args.a["idUser"];
    String value = args.value;
    return SizedBox(
      child: Scaffold(
        body: search
            ? getFutureBuilderSearch(context, value, _searchController.text)
            : getFutureBuilder(context, value),
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
    List<String> values = snapshot.data;
    return ListView.builder(
      itemCount: values.length,
      itemBuilder: (BuildContext context, int index) {
        return Column(
          children: <Widget>[
            ListTile(
              title: Text(values[index].split(" - ")[2] +
                  " - " +
                  values[index].split(" - ")[3]),
              onTap: () {
                idProduto = values[index]
                    .toString()
                    .split(" - ")[0]
                    .replaceAll("#", "");
                idLoja = values[index].toString().split(" - ")[1];
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

  FutureBuilder getFutureBuilder(BuildContext context, String nome) {
    return FutureBuilder(
      future: ProdutosLojaModel.findLojasByProduto(nome),
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

  FutureBuilder getFutureBuilderSearch(
      BuildContext context, String nomeProduto, String nomeLoja) {
    return FutureBuilder(
      future:
          ProdutosLojaModel.findLojasByProdutoAndName(nomeProduto, nomeLoja),
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

  void _showProductInfo() async {
    ProdutoModel.findProductById(idProduto).then((value) => {
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
                ),
                ElevatedButton(
                    child: const Text('Comprar'),
                    onPressed: () {
                      _callCompra();
                    })
              ],
            ),
          );
        });
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

  void _callSearch() async {
    setState(() {
      search = _searchController.text == "" ? false : true;
    });
  }

  void _callCompra() async {
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
                const Text("Comprar:",
                    style: TextStyle(
                      fontSize: 14.0,
                      color: Colors.green,
                      letterSpacing: 2.0,
                      fontWeight: FontWeight.w300,
                    )),
                TextField(
                  controller: _quantidadeController,
                  readOnly: false,
                  decoration: const InputDecoration(labelText: 'Quantidade'),
                ),
                TextField(
                  controller: _pesoEscolhidoController,
                  readOnly: false,
                  keyboardType:
                      const TextInputType.numberWithOptions(decimal: true),
                  decoration: const InputDecoration(labelText: 'Peso'),
                ),
                TextField(
                  controller: _dataEntregaController,
                  readOnly: false,
                  decoration:
                      const InputDecoration(labelText: 'Data de entrega'),
                ),
                TextField(
                  controller: _periodicidadeController,
                  readOnly: false,
                  decoration: const InputDecoration(labelText: 'Periodicidade'),
                ),
                TextField(
                  controller: _numeroEntregasController,
                  readOnly: false,
                  decoration:
                      const InputDecoration(labelText: 'Numero de entregas:'),
                ),
                ElevatedButton(
                    child: const Text('Comprar'),
                    onPressed: () {
                      quantidade = int.parse(_quantidadeController.text);
                      preco = _precoController.text;
                      _callFinalizarCompra();
                    })
              ],
            ),
          );
        });
  }

  void _callFinalizarCompra() async {
    PedidoModel pedidoModel = PedidoModel(
        idPedido: 0,
        data: _dataEntregaController.text,
        periodicidade: _periodicidadeController.text,
        peso: double.parse(_pesoController.text),
        fkCliente: idUser,
        fkVendedor: int.parse(idLoja.replaceAll(" #", "")));
    PedidoModel.addPedido(pedidoModel).then((value) => {
      ProdutoPedidoModel.addProdutoPedido(ProdutoPedidoModel(idProdutoPedido: 0,
        fkProduto: int.parse(idProduto), fkPedido: jsonDecode(value)["idPedido"],
          quantidade: quantidade, peso: jsonDecode(value)["peso"], preco: double.parse(preco))),
      if (value == "Error")
            {
              showDialog(
                  context: context,
                  builder: (BuildContext context) {
                    return const AlertDialog(
                      content: Text("Email ou senha incorreta"),
                    );
                  }),
            },
          Navigator.of(context).pop()
        });
  }
}
