import cv2
import requests
import time

# Configurações do Backend ClickPass [cite: 44, 53]
JAVA_URL = "http://localhost:8080/api/passagens/1/status"

# Carrega o detector de rostos padrão 
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

video_capture = cv2.VideoCapture(0)
print("ClickPass: Aguardando reconhecimento facial na entrada...")

while True:
    ret, frame = video_capture.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    
    # Detecta rostos no vídeo 
    faces = face_cascade.detectMultiScale(gray, 1.1, 4)

    for (x, y, w, h) in faces:
        # Desenha um quadrado no rosto (Simulando o Face ID do PDF) [cite: 16, 21]
        cv2.rectangle(frame, (x, y), (x+w, y+h), (255, 0, 0), 2)
        cv2.putText(frame, "Processando Face ID...", (x, y-10), cv2.FONT_HERSHEY_SIMPLEX, 0.9, (255, 0, 0), 2)
        
        # Simula o tempo de processamento da biometria [cite: 4]
        print("Rosto detectado! Autenticando...")
        time.sleep(2) 
        
        # Envia a requisição REST para o Java [cite: 75, 76]
        try:
            payload = {'novoStatus': 'NO_TERMINAL'}
            response = requests.put(JAVA_URL, params=payload)
            
            if response.status_code == 200:
                print("Sucesso: Status ClickPass atualizado para NO_TERMINAL!") [cite: 17, 77]
                video_capture.release()
                cv2.destroyAllWindows()
                exit()
        except Exception as e:
            print("Erro: Certifique-se que o Java está rodando no IntelliJ.")

    cv2.imshow('ClickPass - Entrada Biometrica', frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

video_capture.release()
cv2.destroyAllWindows()