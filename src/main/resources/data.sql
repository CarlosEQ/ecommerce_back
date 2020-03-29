INSERT INTO categories (name)
SELECT * FROM (SELECT 'Sports') AS tmp
WHERE NOT EXISTS (
    SELECT id FROM categories WHERE name = 'Sports'
) LIMIT 1;

INSERT INTO categories (name)
SELECT * FROM (SELECT 'Vehicles') AS tmp
WHERE NOT EXISTS (
    SELECT id FROM categories WHERE name = 'Vehicles'
) LIMIT 1;

INSERT INTO categories (name)
SELECT * FROM (SELECT 'Technology') AS tmp
WHERE NOT EXISTS (
    SELECT id FROM categories WHERE name = 'Technology'
) LIMIT 1;
INSERT INTO categories (name)
SELECT * FROM (SELECT 'Beauty') AS tmp
WHERE NOT EXISTS (
    SELECT id FROM categories WHERE name = 'Beauty'
) LIMIT 1;

INSERT INTO categories (name)
SELECT * FROM (SELECT 'Toys') AS tmp
WHERE NOT EXISTS (
    SELECT id FROM categories WHERE name = 'Toys'
) LIMIT 1;