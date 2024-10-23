public class Conta  implements IConta{
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected double limiteDeEmprestimo = 1000;
    protected double divida ;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }
    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    @Override
    public void imprimirExtrato() {

    }

    @Override
    public void emprestimo(double valor) {
        if (valor > limiteDeEmprestimo){
            System.out.println("Valor excede o limite de emprestimo");
        }else {
            limiteDeEmprestimo -= valor;
            divida += valor ;
        }
    }

    @Override
    public void pagarEmprestimo(double valor) {
        if(valor > divida){
            System.out.println("Valor maior do que a divida");
        }else {
            limiteDeEmprestimo += valor;
            divida -= valor;
        }
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
        System.out.println(String.format("Limite de emprestimo: %.2f", this.limiteDeEmprestimo));
        System.out.println(String.format("Divida: %.2f", this.divida));
    }
}
