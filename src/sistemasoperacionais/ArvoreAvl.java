package sistemasoperacionais;

import java.util.ArrayList;

public class ArvoreAvl {

  protected No raiz;
  protected boolean encontrado;

	public void inserir(int k) {
		No n = new No(k);
		inserirAVL(this.raiz, n);
                //if(this.raiz == null){
                //    inserirBinario(n, k);
                //    this.setRaiz(n);
                //}
                //inserirBinario(this.raiz, k);
	}

	public void inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.raiz = aInserir;
		} else {
			if (aInserir.getChave() < aComparar.getChave()) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					//verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}

			} else if (aInserir.getChave() > aComparar.getChave()) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					//verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getDireita(), aInserir);
				}

			} else {
				// O nó já existe
			}
		}
	}

        public void inserirBinario(No node, int valor) {
        //verifica se a árvore já foi criada
        
        if (node != null) {
            //Verifica se o valor a ser inserido é menor que o nodo corrente da árovre, se sim vai para subarvore esquerda
            if (valor < node.getChave()) {
                //Se tiver elemento no nodo esquerdo continua a busca
                if (node.getEsquerda() != null) {
                    inserirBinario(node.getEsquerda(), valor);
                } else {
                    //Se nodo esquerdo vazio insere o novo nodo aqui
                    //System.out.println("  Inserindo " + valor + " a esquerda de " + node.getChave());
                    node.setEsquerda(new No(valor)); // = new No(valor);
                }
                //Verifica se o valor a ser inserido é maior que o nodo corrente da árvore, se sim vai para subarvore direita
            }else if (valor > node.getChave()) {
                //Se tiver elemento no nodo direito continua a busca
                if (node.getDireita() != null) {
                    inserirBinario(node.getDireita(), valor);
                } else {
                    //Se nodo direito vazio insere o novo nodo aqui
                    //System.out.println("  Inserindo " + valor + " a direita de " + node.getChave());
                    node.setDireita(new No(valor)); // = new No(valor);
                }
            }
        }
        }
        
	public void verificarBalanceamento(No atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	public void remover(int k) {
		removerAVL(this.raiz, k);
	}

	public void removerAVL(No atual, int k) {
		if (atual == null) {
			return;
		} else {

			if (atual.getChave() > k) {
                                System.out.println(atual.getChave());
				removerAVL(atual.getEsquerda(), k);

			} else if (atual.getChave() < k) {
                                System.out.println(atual.getChave());
				removerAVL(atual.getDireita(), k);

			} else if (atual.getChave() == k) {
                                System.out.println(atual.getChave());
				removerNoEncontrado(atual);
			}
		}
	}

	public void removerNoEncontrado(No aRemover) {
		No r;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

			if (aRemover.getPai() == null) {
				this.raiz = null;
				aRemover = null;
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}

		No p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			this.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}

	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No q) {
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	public int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	final protected ArrayList<No> inorder() {
		ArrayList<No> ret = new ArrayList<No>();
		inorder(raiz, ret);
		return ret;
	}

	final protected void inorder(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		inorder(no.getEsquerda(), lista);
		lista.add(no);
		inorder(no.getDireita(), lista);
	}
        
        public ArrayList<No> buscaInorder(int valor, boolean encontrado){
            ArrayList<No> ret = new ArrayList<No>();
            buscaInorder(raiz, ret, valor);
            return ret;
        }
        
        public boolean buscaInorder(No no, ArrayList<No> lista, int valor){
            if (no == null) {
			return false;
            }
            buscaInorder(no.getEsquerda(), lista, valor);
            lista.add(no);
            if(no.getChave() == valor){
                System.out.println("Valor " + valor + " encontrado!");
                this.encontrado = true;
                return true;
            }
            buscaInorder(no.getDireita(), lista, valor);
            return this.encontrado;
        }
        
        public void imprimirArvore(){
            System.out.println(inorder());
        }
        
        public No getRaiz(){
            return this.raiz;
        }
        
        public void setRaiz(No no){
            this.raiz = no;
        }
        
        @Override
        public String toString(){
            
            return "";
        }
        
}
