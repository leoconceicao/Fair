import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Perfil extends StatefulWidget {
  const Perfil({Key? key}) : super(key: key);

  @override
  _PerfilState createState() => _PerfilState();
}

class _PerfilState extends State<Perfil> {
  @override
  Widget build(BuildContext context) {
    return Container(
        height: 500,
        child: Scaffold(
        body: SafeArea(
          child: Column(
            children: [
              Container(
              ),
              /*Container(
                decoration: BoxDecoration(
                    image: DecorationImage(
                        image: NetworkImage(
                            "https://www.canaldapeca.com.br/blog/wp-content/uploads/2014/01/Curiosidades-Canal-da-Pe%C3%A7a-A-hist%C3%B3ria-do-Camaro.jpg"
                        ),
                        //fit: BoxFit.cover
                    )
                ),
                child: Container(
                  width: double.infinity,
                  height: 150,
                  child: Container(
                    alignment: Alignment(0.0,2.5),
                    child: CircleAvatar(
                      backgroundImage: NetworkImage(
                          "https://media-exp1.licdn.com/dms/image/C4E03AQEJCzI5uFl1Jg/profile-displayphoto-shrink_200_200/0/1600276692983?e=2147483647&v=beta&t=mBy4eOSgBKIJEQrlpLOGj2JT7P5RWLIVq5UpwvcUpOk"
                      ),
                      radius: 60.0,
                    ),
                  ),
                ),
              ),*/
              SizedBox(
                height: 30,
              ),
              Text(
                "Gustavo Bulhmann" //Trocar pelo nome do usuário
                ,style: TextStyle(
                  fontSize: 20.0,
                  color:Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w400

              ),
              ),
              SizedBox(
                height: 10,
              ),
              Text(
                "Maputo, Angola" // Trocar pela cidade do usuário
                ,style: TextStyle(
                  fontSize: 14.0,
                  color:Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300,
              ),
              ),
              SizedBox(
                height: 10,
              ),
              Text(
                "Mestre do Bitcoin"
                ,style: TextStyle(
                  fontSize: 18.0,
                  color:Colors.green,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300
              ),
              ),
              SizedBox(
                height: 10,
              ),
              Card(
                  margin: EdgeInsets.symmetric(horizontal: 20.0,vertical: 8.0),
                  elevation: 2.0,
                  child: Padding(
                      padding: EdgeInsets.symmetric(vertical: 12,horizontal: 30),
                      child: Text("Skill Sets",style: TextStyle(
                          letterSpacing: 2.0,
                          fontWeight: FontWeight.w300
                      ),))
              ),
              SizedBox(
                height: 15,
              ),
              Text(
                "Endereço" // Trocar pro endereço do usuário
                ,style: TextStyle(
                  fontSize: 18.0,
                  color:Colors.black45,
                  letterSpacing: 2.0,
                  fontWeight: FontWeight.w300
              ),
              ),
            ],
          ),
        )
    )
    );
  }
}