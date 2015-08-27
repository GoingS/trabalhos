eleva2 :: [Int] -> [Int]
eleva2 [] = []
eleva2 lis = (head lis)^2 : eleva2 (tail lis)



contido :: Char -> String -> Bool
contido c [] = False
contido c (s:sx) = (s == c) || (contido c sx) 



vogal :: Char -> Bool
vogal c = (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u')

semVogais :: String -> String
semVogais s
   | null s = ""
   | vogal (head s) = (semVogais (tail s))
   | not (vogal (head s)) = (head s) : (semVogais (tail s))



translate :: [(Float, Float)] -> [(Float, Float)]
translate [] = []
translate (t:tx) = (fst t + 2, snd t + 2) : translate tx


geraTabela :: Int -> [(Int, Int)]
geraTabela n = aux 1 n

aux :: Int -> Int -> [(Int, Int)]
aux e n = if (e <= n)
          then (e,e^2) : aux (e + 1) n
          else []
