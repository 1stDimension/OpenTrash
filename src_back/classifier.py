import io

import torch
from PIL import Image
from torchvision import models
from torchvision import transforms


def classify(image, labels):
    model = models.googlenet(pretrained=True)
    transform = transforms.Compose([
        transforms.Resize(256),
        transforms.CenterCrop(224),
        transforms.ToTensor(),
        transforms.Normalize(
            mean=[0.485, 0.456, 0.406],
            std=[0.229, 0.224, 0.225]
        )])
    img = Image.open(io.BytesIO(image))
    img_t = transform(img)
    batch = img_t.unsqueeze(0)

    model.eval()

    with torch.no_grad():
        output  = model(batch)
    out = model(batch)
    _, index = torch.max(out, 1)

    return labels[index]
