import 'package:fair_app/commons/Theme.dart';
import 'package:fair_app/commons/widgets/PrimaryButton.dart';
import 'package:fair_app/commons/widgets/ResetForm.dart';
import 'package:flutter/material.dart';

import 'Login.dart';

class ResetPasswordScreen extends StatelessWidget {
  const ResetPasswordScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: kDefaultPadding,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(
              height: 250,
            ),
            Text(
              'Resetar senha',
              style: titleText,
            ),
            const SizedBox(
              height: 5,
            ),
            Text(
              'Por favor informe seu email',
              style: subTitle.copyWith(fontWeight: FontWeight.w600),
            ),
            const SizedBox(
              height: 10,
            ),
            const ResetForm(),
            const SizedBox(
              height: 40,
            ),
            GestureDetector(
                onTap: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => const LogInScreen(),
                      ));
                },
                child: const PrimaryButton(buttonText: 'Resetar senha')),
          ],
        ),
      ),
    );
  }
}
