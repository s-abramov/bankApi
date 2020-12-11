INSERT INTO client(self_id, client_name) VALUES
    (0, 'CLIENT 1'),
    (1, 'CLIENT 2');

INSERT INTO account(self_id, client_id, account_number , account_balance) VALUES
    (0, 0, '11111111111111111111', 1000.00),
    (1, 1, '44444444444444444444', 1000.00);

INSERT INTO card(self_id, account_id, card_number) VALUES
    (0, 0, '1111222233334444'),
    (1, 1, '9999111122223333');