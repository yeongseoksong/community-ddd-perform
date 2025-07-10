DELIMITER $$
CREATE PROCEDURE insert_dummy_posts_category_status(IN num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE title VARCHAR(255);
    DECLARE content VARCHAR(255);

    WHILE i < num DO
        SET title = CONCAT(
            ELT(FLOOR(1 + RAND()*10), '건강', '운동', '헬스', '다이어트', '트레이닝', '효과적인', '초보자', '고급자', '필수', '추천'),
            ' ',
            ELT(FLOOR(1 + RAND()*10), '비법', '팁', '방법', '프로그램', '가이드', '루틴', '계획', '조언', '노하우', '전략')
        );
        SET content = CONCAT(
            ELT(FLOOR(1 + RAND()*10), '운동은', '건강에', '매우', '중요합니다.', '꾸준한', '트레이닝으로', '목표를', '달성하세요.', '전문가의', '조언을'),
            ' ',
            ELT(FLOOR(1 + RAND()*10), '도움이 됩니다.', '참고하세요.', '성공할 수 있습니다.', '쉽게 따라 하세요.', '건강해집니다.', '효과가 있습니다.', '반드시 해보세요.', '꾸준히 하세요.', '잘 설계된 프로그램입니다.', '전문가 추천입니다.')
        );

INSERT INTO post (
    is_premium, created_at, dislike_count, like_count, updated_at, view_count,
    title, author_id, author_name, category_id, content, prev_status, status
) VALUES (
             FLOOR(RAND()*2),
             NOW() - INTERVAL FLOOR(RAND()*365) DAY,
             FLOOR(RAND()*1000),
             FLOOR(RAND()*1000),
             NOW() - INTERVAL FLOOR(RAND()*365) DAY,
             FLOOR(RAND()*10000),
             title,
             CONCAT('author_', FLOOR(RAND()*1000)),
             CONCAT('작성자 ', FLOOR(RAND()*1000)),
             'sUngys3k',         -- 고정 category_id
             content,
             ELT(FLOOR(RAND()*5)+1, 'DELETED', 'DRAFT', 'EDITED', 'HOT', 'PUBLISHED'),
             'PUBLISHED'          -- 고정 status
         );

SET i = i + 1;
END WHILE;
END $$

DELIMITER ;