-- 1. CATEGORIAS (10 registros)
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

-- Ajusta as sequências
SELECT setval(pg_get_serial_sequence('categoria', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM categoria;
