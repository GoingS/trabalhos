import Text.Printf

type Point = (Int, Int)    
type Size = (Int, Int)
type Hsl = (Int,Int,Int)        
type Rect = (Point, Size, Hsl)   

pos :: (Eq a) => a -> [a] -> Int                                   -- Função simples para retornar a posição de um elemento
pos x lis 
   |x == (head lis) = 0
   |otherwise = 1 + (pos x (tail lis))
--------------------------------------------------------------------------------------
hslToRgb :: (Float, Float, Float) -> (Int, Int, Int)
hslToRgb (h, s, l) = (floor $ 255 * hueToRgb (m1,m2, h+1/3),
                      floor $ 255 * hueToRgb (m1,m2, h)    ,  
                      floor $ 255 * hueToRgb (m1,m2, h-1/3) )
   where 
      m2 = if l<0.5 
           then l*(s+1)
           else l+s-l*s                                             -- Fórmula extraída do site no material de apoio
      m1 = l*2-m2                                                   -- http://www.w3.org/TR/css3-color/#hsl-color

hueToRgb :: (Float, Float, Float) -> (Float)
hueToRgb (m1,m2,h)
   |h'* 6 < 1 = m1+(m2-m1)*h'*6
   |h'* 2 < 1 = m2
   |h'* 3 < 2 = m1+(m2-m1)*(2/3 - h') * 6
   |otherwise = m1
      where h' = if h < 0 then h+1 else (if h > 1 then h-1 else h)
---------------------------------------------------------------------------------------



writeRect :: Rect -> String                                        -- Transforma um tipo Rect em código svg
writeRect ((x,y),(rectW,rectH),(h,s,l)) 
           = printf "    <rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"fill:rgb(%d,%d,%d)\"/>\n" x y rectW rectH r g b
                  where (r,g,b) = hslToRgb ((fromIntegral h)/360, (fromIntegral s)/100,(fromIntegral l)/100)
                                                                   -- Formato extraído de http://www.w3schools.com/svg/svg_rect.asp

writeRects :: [Rect] -> String                                     -- Transforma uma lista do tipo rect em código svg
writeRects lis =  concat (map (writeRect) lis)




createPallete :: (Size, Int, Int, Int) -> (String, Size)           -- Tamanho -> Colunas -> Linhas -> Hue
createPallete ((w,h),c,l,hue) =  
      ((writeRect ((0,0),(w',h'),(60,80,7))) ++            -- retângulo cinza que fica atrás da paleta
      (writeRects [((x,y),(rectW,rectH),(hue, saturations !! (pos x posX), lights !! (pos y posY))) | x <- posX , y <- posY]),(w,h))
                       
                       where w' = last posX + rectW + space                         -- w para que não sobre espaços a direita
                             h' = last posY + rectH + space                         -- h para que não sobre espaços a baixo
                             rectW = (w - (space*c + space)) `div` c                -- largura dos retângulos
                             rectH = (h - (space*l + space)) `div` l                -- altura  
                             space = ((w+h) `div` (c+l)) `div` 15                   -- espaço (baseado no tamanho da matriz)
                             posX = [space,rectW+2*space..w-rectW]                  -- posições x onde retângulos vão ficar
                             posY = [space,rectH+2*space..h-rectH]                  --   ""     y     ""
                             saturations = [100, 100 - (100 `div` (c-1))..0]        -- as duas listas contem as porcentages sat. e 
                             lights = [100,100 - (100 `div` (l-1))..0]              -- lights que vão ser usadas e são escolhidas pela
                                                                                    -- posição da coluna e linha do retângulo sendo escrito

-- Digite 'createSvg $ createPallete ((500,500),6,8,220)' por exemplo.
           
createSvg :: (String,Size) -> IO ()          
createSvg (str,(w,h)) = writeFile "colours.svg" 
       ((printf "<svg width= \"%d\" height=\"%d\" xmlns=\"http://www.w3.org/2000/svg\">\n" w h) ++
       (str) ++    
       ("\n</svg>"))

-- Códigos do início e fim foram pegos de um editor online svg http://svg-edit.googlecode.com/svn-history/r1771/trunk/editor/svg-editor.html
