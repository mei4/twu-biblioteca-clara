1)
SELECT m.name FROM Book b JOIN Checkout_item c ON b.id=c.book_id JOIN Member m ON c.member_id=m.id WHERE b.TITLE="The Hobbit";
Anand Beck

2)
SELECT COUNT(*) from Member m WHERE NOT EXISTS (SELECT * FROM Checkout_item WHERE m.id=member_id);
37

3)
SELECT b.title FROM Book b WHERE NOT EXISTS (SELECT * FROM Checkout_item WHERE book_id=b.id)
UNION
SELECT m.title FROM Movie m WHERE NOT EXISTS (SELECT * FROM Checkout_item WHERE movie_id=m.id);
1984
Catcher in the Rye
Crouching Tiger, Hidden Dragon
Domain Driven Design
Fellowship of the Ring
Lawrence of Arabia
Office Space
Thin Red Line
To Kill a Mockingbird
Tom Sawyer

4)
INSERT INTO Book (id, title) VALUES(100, 'The Pragmatic Programmer');
INSERT INTO Member (id, name) VALUES (99, 'Clara Gaset');
INSERT INTO Checkout_item (member_id, book_id) VALUES(99, 100);
SELECT m.name FROM Book b, Checkout_item c, Member m WHERE b.TITLE="The Pragmatic Programmer" AND c.book_id = b.id AND m.id = c.member_id;
Clara Gaset

5)
SELECT m.name FROM Member m WHERE 1 < (SELECT COUNT(*) FROM Checkout_item c WHERE m.id = c.member_id);
Anand Beck
Frank Smith