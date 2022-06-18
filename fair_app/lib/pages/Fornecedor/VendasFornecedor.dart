import 'dart:collection';

import 'package:fair_app/models/ProdutosPedidoModel.dart';
import 'package:flutter/material.dart';

import '../../commons/ScreenArguments.dart';
import '../../models/PedidoModel.dart';

class VendasFornecedor extends StatefulWidget {
  const VendasFornecedor({Key? key}) : super(key: key);

  @override
  _VendasFornecedorState createState() => _VendasFornecedorState();
}

class _VendasFornecedorState extends State<VendasFornecedor> {
  final bool _canShowButton = true;
  final TextEditingController _searchController = TextEditingController();
  final bool _hasNextPage = true;
  final bool _isFirstLoadRunning = false;
  bool _isLoadMoreRunning = false;
  bool search = false;

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
    return SizedBox(
      height: 500,
      child: Scaffold(
        body: search
            ? getFutureBuilderSearch(context, _searchController.text)
            : getFutureBuilder(context),
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
              title: Text(values[index]),
              onTap: () {
                Navigator.pushNamed(context, '/produtospedido',
                    arguments: ScreenArguments('idProduto', values[index],HashMap()));
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

  FutureBuilder getFutureBuilder(BuildContext context) {
    return FutureBuilder(
      future: PedidoModel.findPedidos(),
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

  FutureBuilder getFutureBuilderSearch(BuildContext context, String name) {
    return FutureBuilder(
      future: ProdutoPedidoModel.findProdutosByName(name),
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

  void _callSearch() async {
    setState(() {
      search = _searchController.text == "" ? false : true;
    });
  }
}