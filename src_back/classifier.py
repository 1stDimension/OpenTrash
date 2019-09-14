import io

import torch
from PIL import Image
from torchvision import models
from torchvision import transforms


def classify(image, labels):
    alexnet = models.alexnet(pretrained=True)
    transform = transforms.Compose([
        transforms.Resize(512),
        transforms.CenterCrop(256),
        transforms.ToTensor()
        ])
    img = Image.open(io.BytesIO(image))
    img_t = transform(img)
    batch_t = torch.unsqueeze(img_t, 0)

    alexnet.eval()
    out = alexnet(batch_t)
    _, index = torch.max(out, 1)

    return labels[index]
