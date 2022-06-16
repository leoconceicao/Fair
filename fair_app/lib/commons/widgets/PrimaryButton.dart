import 'package:flutter/material.dart';

import '../Theme.dart';

class PrimaryButton extends StatelessWidget {
  final String buttonText;
  const PrimaryButton({Key? key, required this.buttonText}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      height: MediaQuery.of(context).size.height * 0.08,
      width: double.infinity,
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(16), color: Colors.green),
      child: Text(
        buttonText,
        style: textButton.copyWith(color: kWhiteColor),
      ),
    );
  }
}
