import 'dart:async';

import 'dart:convert';

import 'package:http/http.dart' as http;

class ProdutoModel {
  final int idProduto;
  final String nome;
  final String tipo;
  final double preco;
  final String foto;
  final String validade;
  final double peso;

  const ProdutoModel({
    required this.idProduto,
    required this.nome,
    required this.tipo,
    required this.preco,
    required this.foto,
    required this.validade,
    required this.peso,
  });
}

