-- 1. CORRENTISTAS
INSERT INTO correntista (id, nome, username, senha, role, bloqueado) VALUES
(1, 'Melquisedeque Vital', 'melquisedeque@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_ADMINISTRADOR', false),
(2, 'Mariana Ludmilla', 'ludmilla@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_ADMINISTRADOR', false),
(3, 'Fred', 'fred@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_CORRENTISTA', false),
(4, 'Victor', 'victor@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_CORRENTISTA', false),
(5, 'Cauê', 'caue@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_CORRENTISTA', false),
(6, 'Rogério', 'rogerio@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_CORRENTISTA', false),
(7, 'Mikael', 'mikael@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_CORRENTISTA', false),
(8, 'Murilo', 'murilo@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_CORRENTISTA', false),
(9, 'Felipe', 'felipe@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_CORRENTISTA', false),
(10, 'Nabucodonosor', 'nabucodonosor@gmail.com', '$2a$12$otXSj0SovAxN8w49wtjKse.ExE.8OSXQmP1ye8/6RAVs3SYXPnCJ2', 'ROLE_CORRENTISTA', true)
ON CONFLICT (id) DO NOTHING;

-- 2. NATUREZA
INSERT INTO natureza (id, tipo_natureza) VALUES
(1, 'Entrada'),
(2, 'Saida'),
(3, 'Investimento')
ON CONFLICT (id) DO NOTHING;

-- 3. CATEGORIAS
INSERT INTO categoria (id, nome, natureza_id, ativo, ordem) VALUES
-- ENTRADAS (natureza_id = 1)
(1, 'Salário', 1, true, 1),
(2, 'Cashback', 1, true, 2),
(3, 'Resgate Investimento', 1, true, 3),
(4, 'Outras Entradas', 1, true, 4),

-- SAÍDAS (natureza_id = 2)
(5, 'Saúde e Remédios', 2, true, 1),
(6, 'Academia e Personal', 2, true, 2),
(7, 'Carros e Uber', 2, true, 3),
(8, 'Educação e Cursos', 2, true, 4),
(9, 'Lazer e Turismo', 2, true, 5),
(10, 'Condomínio', 2, true, 6),
(11, 'Energia', 2, true, 7),
(12, 'Celular', 2, true, 8),
(13, 'Internet', 2, true, 9),
(14, 'Itens Pessoais', 2, true, 10),
(15, 'Feira', 2, true, 11),
(16, 'Casa', 2, true, 12),
(17, 'Impostos', 2, true, 13),
(18, 'Outros gastos', 2, true, 14),

-- INVESTIMENTOS (natureza_id = 3)
(19, 'Aporte Renda Fixa', 3, true, 1),
(20, 'Aporte Renda Variável', 3, true, 2),
(21, 'Aporte Reserva Emergencia', 3, true, 3),
(22, 'Aporte Previdência', 3, true, 4)
ON CONFLICT (id) DO NOTHING;

-- 4. CONTAS / CARTOES DE CREDITO (Inclusão das colunas tipo_conta e bloqueado)
INSERT INTO conta (dtype, id, numero, descricao, tipo_conta, correntista_id, dia_fechamento, limite_credito) VALUES
('Conta', 1, '1001', 'Conta Principal - Melquisedeque', 'CORRENTE', 1, null, null),
('CartaoDeCredito', 2, '1002', 'Cartão Nubank - Melquisedeque', 'CREDITO', 1, 5, 8000.00),
('Conta', 3, '1003', 'Conta Itaú - Mariana', 'CORRENTE', 2, null, null),
('CartaoDeCredito', 4, '1004', 'Cartão XP Black - Fred', 'CREDITO', 3, 10, 50000.00),
('Conta', 5, '1005', 'Conta Corrente Banco do Brasil - Fred', 'CORRENTE', 3, null, null),
('Conta', 6, '1006', 'Conta Salário - Victor', 'CORRENTE', 4, null, null),
('CartaoDeCredito', 7, '1007', 'Cartão Inter - Cauê', 'CREDITO', 5, 15, 3000.00),
('Conta', 8, '1008', 'Conta Santander - Rogério', 'CORRENTE', 6, null, null),
('Conta', 9, '1009', 'Conta Nubank - Mikael', 'CORRENTE', 7, null, null),
('CartaoDeCredito', 10, '1010', 'Cartão C6 - Murilo', 'CREDITO', 8, 20, 12000.00)
ON CONFLICT (id) DO NOTHING;

-- 5. TRANSACOES
INSERT INTO transacao (id, data, descricao, valor, conta_id, categoria_id) VALUES
-- Transações do Administrador Melquisedeque (Conta 1 e Cartão 2)
(1, '2025-01-05', 'Salário Executivo', 12500.00, 1, 1),
(2, '2025-01-08', 'Supermercado Central', 850.40, 1, 15),
(3, '2025-01-12', 'Aporte Reserva', 2000.00, 1, 21),
(4, '2025-01-15', 'Restaurante Paris', 320.00, 2, 9),

-- Transações da Administradora Mariana (Conta 3)
(5, '2025-01-05', 'Salário Gestão', 11000.00, 3, 1),
(6, '2025-01-10', 'Plano de Saúde', 650.00, 3, 5),
(7, '2025-01-18', 'Curso de Tecnologia', 1200.00, 3, 8),

-- Transações do Fred (Conta 5 e Cartão 4)
(8, '2025-01-02', 'Salário Diretor Tech', 35000.00, 5, 1),
(9, '2025-01-04', 'Projeto Consultoria Internacional', 28000.00, 5, 4),
(10, '2025-01-06', 'Bónus de Performance', 15000.00, 5, 4),
(11, '2025-01-10', 'Aporte Renda Fixa High Yield', 10000.00, 5, 19),
(12, '2025-01-15', 'Aporte Ações B3', 15000.00, 5, 20),
(13, '2025-01-20', 'Jantar de Celebração', 1450.00, 4, 9),
(14, '2025-01-22', 'Posto de Gasolina', 380.00, 4, 7),
(15, '2025-01-25', 'Condomínio de Luxo', 2200.00, 5, 10),

-- Transações dos outros Correntistas
(16, '2025-01-05', 'Salário Dev', 6200.00, 6, 1),
(17, '2025-01-07', 'Feira Quinzenal', 420.00, 6, 15),
(18, '2025-01-05', 'Salário Analista', 4800.00, 8, 1),
(19, '2025-01-12', 'Mensalidade Academia', 180.00, 7, 6),
(20, '2025-01-14', 'Conta de Energia', 290.50, 9, 11),
(21, '2025-01-19', 'Cashback Compras', 125.30, 9, 2),
(22, '2025-01-21', 'Fatura Telemóvel', 110.00, 10, 12)
ON CONFLICT (id) DO NOTHING;

-- 6. COMENTARIOS
INSERT INTO comentario (id, texto, transacao_id) VALUES
(1, 'Pagamento do salário referente ao mês de Janeiro', 1),
(2, 'Almoço de negócios com clientes', 4),
(3, 'Combustível para viagem de final de semana', 14),
(4, 'Aluguel do escritório executivo', 15),
(5, 'Aporte mensal no Tesouro Selic', 3),
(6, 'Consultoria técnica de desenvolvimento de software', 9),
(7, 'Mensalidade da pós-graduação', 7),
(8, 'Bónus anual de produtividade', 10),
(9, 'Compra de lote de ações ITUB4 e VALE3', 12),
(10, 'Subscrição de serviço de streaming', 21)
ON CONFLICT (id) DO NOTHING;

-- Sincronização das Sequências PostgreSQL
SELECT setval(pg_get_serial_sequence('correntista', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM correntista;
SELECT setval(pg_get_serial_sequence('natureza', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM natureza;
SELECT setval(pg_get_serial_sequence('categoria', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM categoria;
SELECT setval(pg_get_serial_sequence('conta', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM conta;
SELECT setval(pg_get_serial_sequence('transacao', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM transacao;
SELECT setval(pg_get_serial_sequence('comentario', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM comentario;