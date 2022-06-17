import 'package:flutter/material.dart';

class CheckBox extends StatefulWidget {
  final String text;
  const CheckBox(this.text, {Key? key}) : super(key: key);
  @override
  _CheckBoxState createState() => _CheckBoxState();
}

class _CheckBoxState extends State<CheckBox> {
  bool _isSelected = false;
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Row(
          children: [
            GestureDetector(
              onTap: () {
                setState(() {
                  _isSelected = !_isSelected;
                });
              },
              child: Container(
                width: 20,
                height: 20,
                decoration:
                    BoxDecoration(border: Border.all(color: Colors.black)),
                child: _isSelected
                    ? const Icon(Icons.check, size: 17, color: Colors.green)
                    : null,
              ),
            ),
            const SizedBox(
              width: 12,
            ),
            Text(widget.text),
          ],
        )
      ],
    );
  }
}
