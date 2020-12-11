DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS client;

CREATE TABLE IF NOT EXISTS client(
    self_id BIGINT NOT NULL AUTO_INCREMENT,
    client_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (self_id)
);

CREATE TABLE IF NOT EXISTS account(
    self_id BIGINT NOT NULL AUTO_INCREMENT,
    client_id BIGINT NOT NULL,
    account_number VARCHAR(20),
    account_balance NUMERIC(19, 2),
    PRIMARY KEY (self_id),
    CONSTRAINT fk_client_account FOREIGN KEY(client_id)
        REFERENCES client(self_id) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS card(
    self_id BIGINT NOT NULL AUTO_INCREMENT,
    account_id BIGINT NOT NULL,
    card_number VARCHAR(16),
    PRIMARY KEY (self_id),
    CONSTRAINT fk_card_account FOREIGN KEY(account_id)
        REFERENCES account(self_id) ON UPDATE NO ACTION ON DELETE CASCADE
);