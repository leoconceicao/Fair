// main.dart
import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:math';

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
  // We will fetch data from this Rest api
  final _baseUrl = 'https://jsonplaceholder.typicode.com/posts';

  // At the beginning, we fetch the first 20 posts
  int _page = 0;
  int _limit = 20;

  // There is next page or not
  bool _hasNextPage = true;

  // Used to display loading indicators when _firstLoad function is running
  bool _isFirstLoadRunning = false;

  // Used to display loading indicators when _loadMore function is running
  bool _isLoadMoreRunning = false;

  // This holds the posts fetched from the server
  List _posts = [];

  // This function will be triggered whenver the user scroll
  // to near the bottom of the list view
  void _loadMore() async {
    if (_hasNextPage == true &&
        _isFirstLoadRunning == false &&
        _isLoadMoreRunning == false &&
        _controller.position.extentAfter < 300) {
      setState(() {
        _isLoadMoreRunning = true; // Display a progress indicator at the bottom
      });
      _page += 1; // Increase _page by 1
      setState(() {
        _isLoadMoreRunning = false;
      });
    }
  }

  // The controller for the ListView
  late ScrollController _controller;

  @override
  void initState() {
    super.initState();
    _controller = new ScrollController()..addListener(_loadMore);
  }

  @override
  void dispose() {
    _controller.removeListener(_loadMore);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 500,
      child: Scaffold(
        body:  Column(
          children: [
            Expanded(
              child: ListView.builder(
                controller: _controller,
                itemCount: 10,
                itemBuilder: (_, index) => const Card(
                  child: ListTile(
                      title: Text("Teste"),
                      subtitle: Text("Teste"),
                      tileColor: Colors.white
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
