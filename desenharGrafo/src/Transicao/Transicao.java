/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transicao;

import Estado.Estado;

/**
 *
 * @author Mauricio José
 */
public class Transicao {
    //public Estado estado;
    public String caracterTransicao;
    public Estado estadoIda;
    
    public Transicao(String caracterTransicao, Estado estadoIda) {
        this.caracterTransicao = caracterTransicao;
        this.estadoIda = estadoIda;
    }
}
