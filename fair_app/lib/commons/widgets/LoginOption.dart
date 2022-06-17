import 'package:flutter/material.dart';

class LoginOption extends StatelessWidget {
  const LoginOption({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: const [
        BuildButton(
          iconImage: Image(
            height: 20,
            width: 10,
            image: AssetImage('images/facebook.png'),
          ),
          textButton: 'Facebook',
        ),
        BuildButton(
          iconImage: Image(
            height: 20,
            width: 10,
            image: AssetImage('images/google.png'),
          ),
          textButton: 'Google',
        ),
      ],
    );
  }
}

class BuildButton extends StatelessWidget {
  final Image iconImage;
  final String textButton;
  const BuildButton(
      {Key? key, required this.iconImage, required this.textButton})
      : super(key: key);
  @override
  Widget build(BuildContext context) {
    var mediaQuery = MediaQuery.of(context).size;
    return Container(
      height: 600 * 0.06,
      width: 400 * 0.36,
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(5),
          border: Border.all(color: Colors.grey)),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          iconImage,
          const SizedBox(
            width: 20,
          ),
          Text(textButton),
        ],
      ),
    );
  }
}
