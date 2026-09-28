-- 1. USUARIOS (10 registros)
INSERT INTO usuario (id, nome, username, senha, role, bloqueado) VALUES
(1, 'Administrador', 'admin@admin.com', '123456', 'ROLE_ADMINISTRADOR', false),
(2, 'Melquisedeque Vital', 'melquisedeque@email.com', '123456', 'ROLE_CORRENTISTA', false),
(3, 'João Silva', 'joao@email.com', '123456', 'ROLE_CORRENTISTA', false),
(4, 'Maria Santos', 'maria@email.com', '123456', 'ROLE_CORRENTISTA', false),
(5, 'Pedro Alves', 'pedro@email.com', '123456', 'ROLE_CORRENTISTA', false),
(6, 'Ana Clara', 'ana@email.com', '123456', 'ROLE_CORRENTISTA', false),
(7, 'Lucas Lima', 'lucas@email.com', '123456', 'ROLE_CORRENTISTA', true),
(8, 'Juliana Costa', 'juliana@email.com', '123456', 'ROLE_CORRENTISTA', false),
(9, 'Marcos Pereira', 'marcos@email.com', '123456', 'ROLE_CORRENTISTA', false),
(10, 'Carla Dias', 'carla@email.com', '123456', 'ROLE_CORRENTISTA', false)
ON CONFLICT (id) DO NOTHING;

-- 2. CATEGORIAS (10 registros)
INSERT INTO categoria (id, nome, natureza, ativo) VALUES
(1, 'Salário', 'ENTRADA', true),
(2, 'Alimentação', 'SAIDA', true),
(3, 'Transporte', 'SAIDA', true),
(4, 'Moradia', 'SAIDA', true),
(5, 'Lazer', 'SAIDA', true),
(6, 'Saúde', 'SAIDA', true),
(7, 'Educação', 'SAIDA', true),
(8, 'Freelance', 'ENTRADA', true),
(9, 'Tesouro Direto', 'INVESTIMENTO', true),
(10, 'Ações B3', 'INVESTIMENTO', true)
ON CONFLICT (id) DO NOTHING;

-- 3. CONTAS / CARTOES DE CREDITO (10 registros)
-- Observação: Como CartaoDeCredito herda de Conta sem especificar a estratégia, 
-- o Hibernate usa a estratégia SINGLE_TABLE por padrão, gerando a coluna 'dtype'.
INSERT INTO conta (dtype, id, numero, descricao, correntista_id, dia_fechamento, limite_credito) VALUES
('Conta', 1, 1001, 'Conta Corrente BB', 2, null, null),
('CartaoDeCredito', 2, 1002, 'Cartão Nubank', 2, 5, 5000.00),
('Conta', 3, 1003, 'Poupança Caixa', 3, null, null),
('CartaoDeCredito', 4, 1004, 'Cartão Inter', 3, 10, 2500.00),
('Conta', 5, 1005, 'Conta Corrente Itaú', 4, null, null),
('Conta', 6, 1006, 'Conta Salário Bradesco', 5, null, null),
('CartaoDeCredito', 7, 1007, 'Cartão C6 Bank', 6, 15, 10000.00),
('Conta', 8, 1008, 'Conta Santander', 8, null, null),
('Conta', 9, 1009, 'Conta Nubank', 9, null, null),
('CartaoDeCredito', 10, 1010, 'Cartão XP', 10, 20, 15000.00)
ON CONFLICT (id) DO NOTHING;

-- 4. COMENTARIOS (10 registros)
INSERT INTO comentario (id, texto) VALUES
(1, 'Pagamento referente ao mês de Janeiro'),
(2, 'Almoço com a equipe do projeto'),
(3, 'Abastecimento do carro para viagem'),
(4, 'Aluguel do apartamento 102'),
(5, 'Ingressos para o cinema'),
(6, 'Consulta médica de rotina'),
(7, 'Mensalidade da faculdade'),
(8, 'Projeto de site para cliente X'),
(9, 'Aporte mensal de investimentos'),
(10, 'Compra de 100 ações ITUB4')
ON CONFLICT (id) DO NOTHING;

-- 5. TRANSACOES (10 registros)
INSERT INTO transacao (id, data, descricao, valor, movimento, conta_id, categoria_id, comentario_id) VALUES
(1, '2023-01-05', 'Salário Mensal', 5500.00, 'CREDITO', 1, 1, 1),
(2, '2023-01-06', 'Restaurante Sabor', 120.50, 'DEBITO', 2, 2, 2),
(3, '2023-01-07', 'Posto Ipiranga', 250.00, 'DEBITO', 4, 3, 3),
(4, '2023-01-10', 'Aluguel Mensal', 1500.00, 'DEBITO', 3, 4, 4),
(5, '2023-01-12', 'Cinemark', 80.00, 'DEBITO', 7, 5, 5),
(6, '2023-01-15', 'Clínica Sorriso', 300.00, 'DEBITO', 8, 6, 6),
(7, '2023-01-20', 'Mensalidade IFPB', 400.00, 'DEBITO', 9, 7, 7),
(8, '2023-01-22', 'Job Freelance', 1200.00, 'CREDITO', 1, 8, 8),
(9, '2023-01-25', 'Tesouro IPCA', 500.00, 'DEBITO', 1, 9, 9),
(10, '2023-01-28', 'Compra de Ações', 2500.00, 'DEBITO', 10, 10, 10)
ON CONFLICT (id) DO NOTHING;

-- Ajusta as sequências (seeding com IDs fixos desincroniza a sequência geradora do banco)
SELECT setval(pg_get_serial_sequence('usuario', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM usuario;
SELECT setval(pg_get_serial_sequence('categoria', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM categoria;
SELECT setval(pg_get_serial_sequence('conta', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM conta;
SELECT setval(pg_get_serial_sequence('comentario', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM comentario;
SELECT setval(pg_get_serial_sequence('transacao', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM transacao;
